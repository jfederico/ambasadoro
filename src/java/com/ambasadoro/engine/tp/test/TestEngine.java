package com.ambasadoro.engine.tp.test;

import java.util.Map;
import java.util.HashMap;

import com.ambasadoro.Ambasadoro;
import com.ambasadoro.engine.IEngine;
import com.ambasadoro.engine.VendorCodes;

import org.ambasadoro.lti.ILTIConstants;
import org.ambasadoro.lti.IToolProvider;
import org.ambasadoro.lti.v1_0.*;
import org.json.JSONObject;

public class TestEngine implements IEngine {
    public static final String TP_NAME = "Test";
    public static final String TP_DESCRIPTION = "Tool provider for testing purposes.";
    public static final String TP_URL = "http://test.123it.ca/";
    public static final String TP_CONTACT_EMAIL = "support@123it.ca";

    String code = VendorCodes.TP_CODE_TEST;
    
    ILTIConstants ltiConstants = new LTIConstants();
    IToolProvider toolProvider;
    Map<String, String> properties = new HashMap<String, String>();
    JSONObject meta;

    public TestEngine(Ambasadoro ambasadoro, Map<String, String> params, String endpoint) throws Exception {
        //System.out.println("Creating ltiEngine for [" + code + "]");
        try {
            toolProvider = new ToolProvider(params);
            meta = new JSONObject(ambasadoro.getMeta());
            //meta = new JSONObject("{'meta': { 'properties': { 'toolEndPoint': 'http://localhost/', 'toolKey': 'xxx', 'toolSecret': 'yyy'} } }");
            //System.out.println(meta.get("meta"));
            
            if( !toolProvider.hasValidSignature(endpoint, ambasadoro.getLtiSecret()) )
                throw new Exception("OAuth signature is NOT valid");
            else
                System.out.println("OAuth signature is valid");
        } catch( Exception e) {
            throw e;
        }
    }
    
    public String getCode(){
        return this.code;
    }
    
    public boolean hasAllRequiredParams(){
        return false;
    }
    
}
