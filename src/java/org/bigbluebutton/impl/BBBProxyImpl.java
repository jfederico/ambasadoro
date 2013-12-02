/*
	BigBlueButton - http://www.bigbluebutton.org

	Copyright (c) 2008-2012 by respective authors (see below). All rights reserved.

	BigBlueButton is free software; you can redistribute it and/or modify it under the
	terms of the GNU Lesser General Public License as published by the Free Software
	Foundation; either version 2 of the License, or (at your option) any later
	version.

	BigBlueButton is distributed in the hope that it will be useful, but WITHOUT ANY
	WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
	PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.

	You should have received a copy of the GNU Lesser General Public License along
	with BigBlueButton; if not, If not, see <http://www.gnu.org/licenses/>.

	Author: Jesus Federico <jesus@blindsidenetworks.com>
*/ 
package org.bigbluebutton.impl;

import org.bigbluebutton.api.BBBProxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.apache.commons.codec.digest.DigestUtils;

public class BBBProxyImpl implements BBBProxy{

	String endpoint;
	String secret;

	BBBProxyImpl() {
	    this.endpoint = "http://test-install.blindsidenetworks.com/bigbluebutton/";
	    this.secret = "8cd8ef52e8e101574e400365b55e11a6";
	}

	BBBProxyImpl(String endpoint, String secret) {
	    if( !endpoint.substring(endpoint.length()-1).equals("/") )
	        endpoint += "/";
	    this.endpoint = endpoint;
	    this.secret = secret;
	}

	public void setEndpoint(String endpoint){
        if( !endpoint.substring(endpoint.length()-1).equals("/") )
            endpoint += "/";
	    this.endpoint = endpoint;
	}

	public void setSecret(String secret){
	    this.secret = secret;
	}

	public static String getVersion(String endpoint, String secret){
	    String version;

	    Map<String, Object> mapResponse = doAPICall(endpoint + API_SERVERPATH);
	    System.out.println(mapResponse);
	    version = "0.81";

	    return version;
	}
	
	////////////////////
	/** Make an API call */
    protected static Map<String, Object> doAPICall(String query) {
        Map<String, Object> response = new HashMap<String, Object>();

        StringBuilder urlStr = new StringBuilder(query);
        try {
            // open connection
            //log.debug("doAPICall.call: " + query );
            
            URL url = new URL(urlStr.toString());
            HttpURLConnection httpConnection = (HttpURLConnection) url.openConnection();
            httpConnection.setUseCaches(false);
            httpConnection.setDoOutput(true);
            httpConnection.setRequestMethod("GET");
            httpConnection.connect();

            int responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // read response
                InputStreamReader isr = null;
                BufferedReader reader = null;
                StringBuilder xml = new StringBuilder();
                try {
                    isr = new InputStreamReader(httpConnection.getInputStream(), "UTF-8");
                    reader = new BufferedReader(isr);
                    String line = reader.readLine();
                    while (line != null) {
                        if( !line.startsWith("<?xml version=\"1.0\"?>"))
                            xml.append(line.trim());
                        line = reader.readLine();
                    }
                } finally {
                    if (reader != null)
                        reader.close();
                    if (isr != null)
                        isr.close();
                }
                httpConnection.disconnect();

                // parse response
                //log.debug("doAPICall.responseXml: " + xml);
                //Patch to fix the NaN error
                String stringXml = xml.toString();
                stringXml = stringXml.replaceAll(">.\\s+?<", "><");
                
                DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder docBuilder;
                try {
                    docBuilder = docBuilderFactory.newDocumentBuilder();
                    Document dom = null;
                    dom = docBuilder.parse(new InputSource( new StringReader(stringXml)));
                    
                    response = getNodesAsMap(dom, "response");
                    //log.debug("doAPICall.responseMap: " + response);
                    
                    String returnCode = (String) response.get("returncode");
                    if (BBBProxy.APIRESPONSE_FAILED.equals(returnCode)) {
                        //log.debug("doAPICall." + (String) response.get("messageKey") + ": Message=" + (String) response.get("message"));
                    }

                } catch (ParserConfigurationException e) {
                    //logger.error("Failed to initialise BaseProxy", e)
                }

            } else {
                //log.debug("doAPICall.HTTPERROR: Message=" + "BBB server responded with HTTP status code " + responseCode);
            }

        } catch(IOException e) {
            //log.debug("doAPICall.IOException: Message=" + e.getMessage());
        } catch(SAXException e) {
            //log.debug("doAPICall.SAXException: Message=" + e.getMessage());
        } catch(IllegalArgumentException e) {
            //log.debug("doAPICall.IllegalArgumentException: Message=" + e.getMessage());
        } catch(Exception e) {
            //log.debug("doAPICall.Exception: Message=" + e.getMessage());
        }
        return response;
    }
    
    /** Get all nodes under the specified element tag name as a Java map */
    protected static Map<String, Object> getNodesAsMap(Document dom, String elementTagName) {
        Node firstNode = dom.getElementsByTagName(elementTagName).item(0);
        return processNode(firstNode);
    }

    protected static Map<String, Object> processNode(Node _node) {
        Map<String, Object> map = new HashMap<String, Object>();
        NodeList responseNodes = _node.getChildNodes();
        for (int i = 0; i < responseNodes.getLength(); i++) {
            Node node = responseNodes.item(i);
            String nodeName = node.getNodeName().trim();
            if (node.getChildNodes().getLength() == 1
                    && ( node.getChildNodes().item(0).getNodeType() == org.w3c.dom.Node.TEXT_NODE || node.getChildNodes().item(0).getNodeType() == org.w3c.dom.Node.CDATA_SECTION_NODE) ) {
                String nodeValue = node.getTextContent();
                map.put(nodeName, nodeValue != null ? nodeValue.trim() : null);
            
            } else if (node.getChildNodes().getLength() == 0
                    && node.getNodeType() != org.w3c.dom.Node.TEXT_NODE
                    && node.getNodeType() != org.w3c.dom.Node.CDATA_SECTION_NODE) {
                map.put(nodeName, "");
            
            } else if ( node.getChildNodes().getLength() >= 1
                    && node.getChildNodes().item(0).getChildNodes().item(0).getNodeType() != org.w3c.dom.Node.TEXT_NODE
                    && node.getChildNodes().item(0).getChildNodes().item(0).getNodeType() != org.w3c.dom.Node.CDATA_SECTION_NODE ) {

                List<Object> list = new ArrayList<Object>();
                for (int c = 0; c < node.getChildNodes().getLength(); c++) {
                    Node n = node.getChildNodes().item(c);
                    list.add(processNode(n));
                }
                map.put(nodeName, list);
            
            } else {
                map.put(nodeName, processNode(node));
            }
        }
        return map;
    }
    /////////////
	
    public String getVersionURL(){
        return this.endpoint + API_SERVERPATH;
    }
    
    public String getCreateURL(Map<String, String> params){
        String qs;
        qs = "name=" + params.get("name");
        qs += "&meetingID=" + params.get("meetingID");
        qs += "&moderatorPW=" + params.get("moderatorPW");
        qs += "&attendeePW=" + params.get("attendeePW");
        qs += params.containsKey("welcome")? "&welcome=" + params.get("welcome"): "";
        qs += params.containsKey("logoutURL")? "&logoutURL=" + params.get("logoutURL"): "";
        qs += params.containsKey("voiceBridge")? "&voiceBridge=" + params.get("voiceBridge"): "";
        qs += params.containsKey("dialNumber")? "&dialNumber=" + params.get("dialNumber"): "";
        qs += params.containsKey("duration")? "&duration=" + params.get("duration"): "";
        qs += getCheckSumParameterForQuery(APICALL_CREATE, qs);
        
        return this.endpoint + API_SERVERPATH + APICALL_CREATE + "?" + qs;
    }

    public String getJoinURL(Map<String, String> params){
        String qs;

        qs = "fullName=" + params.get("fullName");
        qs += "&meetingID=" + params.get("meetingID");
        qs += "&password=" + params.get("password");
        qs += params.containsKey("createTime")? "&createTime=" + params.get("createTime"): "";
        qs += params.containsKey("userID")? "&userID=" + params.get("userID"): "";
        qs += getCheckSumParameterForQuery(APICALL_JOIN, qs);
        
        return this.endpoint + API_SERVERPATH + APICALL_JOIN + "?" + qs;
    }
    
	public String getIsMeetingRunningURL(String meetingID) {
	    String qs;
	    qs = "meetingID=" + meetingID;
	    qs += getCheckSumParameterForQuery(APICALL_ISMEETINGRUNNING, qs);
	    
	    return this.endpoint + API_SERVERPATH + APICALL_ISMEETINGRUNNING + "?" + qs;
	}

	public String getEndURL(String meetingID, String password) {
	    String qs;
	    qs = "meetingID=" + meetingID;
	    qs += "&password=" + password;
	    qs += getCheckSumParameterForQuery(APICALL_END, qs);
	    
	    return this.endpoint + API_SERVERPATH + APICALL_END + "?" + qs;
	}

	public String getGetMeetingInfoURL(String meetingID, String password) {
	    String qs;
	    qs = "meetingID=" + meetingID;
	    qs += "&password=" + password;
	    qs += getCheckSumParameterForQuery(APICALL_GETMEETINGINFO, qs);
	    
	    return this.endpoint + API_SERVERPATH + APICALL_GETMEETINGINFO + "?" + qs;
	}

	public String getGetMeetingsURL(String meetingID, String password) {
	    String qs;
	    qs = getCheckSumParameterForQuery(APICALL_END, "");
	    
	    return this.endpoint + API_SERVERPATH + APICALL_END + "?" + qs;
	}

	public String getStringEncoded(String string){
	    String stringEncoded = "";
	    
	    try {
	        stringEncoded = URLEncoder.encode(string, PARAMETERENCODING); 
	    } catch(Exception e){}
	    
	    return stringEncoded;
	}

	/** Creates the checksum parameter to be included as part of the endpoint */
	protected String getCheckSumParameterForQuery(String apiCall, String queryString) {
	    if (this.secret != null)
	        return "&checksum=" + DigestUtils.shaHex(apiCall + queryString + this.secret);
	    else
	        return "";
	}

    public String getGetRecordingsURL(String meetingID) {
        return null;
    }

    public String getPublishRecordingsURL(String recordID, boolean publish) {
        return null;
    }

    public String getDeleteRecordingsURL(String recordID) {
        return null;
    }
}
