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
    public static final String TP_VENDOR_CODE = VendorCodes.TP_CODE_TEST;
    public static final String TP_VENDOR_NAME = "Test";
    public static final String TP_VENDOR_DESCRIPTION = "Tool provider for testing purposes.";
    public static final String TP_VENDOR_URL = "http://test.123it.ca/";
    public static final String TP_VENDOR_CONTACT_EMAIL = "support@123it.ca";

    Ambasadoro ambasadoro;

    ILTIConstants ltiConstants = new LTIConstants();
    IToolProvider toolProvider;
    Map<String, String> properties = new HashMap<String, String>();
    JSONObject tpExtraProperties;
    JSONObject tpRequiredParameters;
    JSONObject tcOverride;

    public TestEngine(Ambasadoro ambasadoro, Map<String, String> params, String endpoint) throws Exception {
        //System.out.println("Creating ltiEngine for [" + code + "]");
        this.ambasadoro = ambasadoro;

        try {
            toolProvider = new ToolProvider(params);
            tpExtraProperties = new JSONObject(ambasadoro.getTpExtraProperties());
            tpRequiredParameters = new JSONObject(ambasadoro.getTpRequiredParameters());
            tcOverride = new JSONObject(ambasadoro.getTcOverride());
            //meta = new JSONObject("{'meta': { 'tp': { 'properties': { 'toolEndPoint': {'value': '', 'type': 'text'}, 'toolKey': {'value': '', 'type': 'text'}, 'toolSecret': {'value': '', 'type': 'text'}, 'emailAllowed': {'value': '', 'type': 'checkbox'} } } } }");
            //meta = new JSONObject("{'meta': { 'tp': { 'properties': { 'toolEndPoint': {'value': '', 'type': 'text'}, 'toolKey': {'value': '', 'type': 'text'}, 'toolSecret': {'value': '', 'type': 'text'}, 'emailAllowed': {'value': '', 'type': 'checkbox'} } } } }");
            //System.out.println(meta.get("tp"));

            if( !toolProvider.hasValidSignature(endpoint, ambasadoro.getLtiSecret()) )
                throw new Exception("OAuth signature is NOT valid");
            else
                System.out.println("OAuth signature is valid");
        } catch( Exception e) {
            throw e;
        }
    }

    public String getToolTitle(){
        return ambasadoro.getTpTitle();
    }

    public String getToolDescription(){
        return ambasadoro.getTpDescription();
    }

    public String getToolVendorCode(){
        return TP_VENDOR_CODE;
    }

    public boolean hasAllRequiredParams(){
        return false;
    }
    
}
