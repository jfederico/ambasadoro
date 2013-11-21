package com.ambasadoro.engine.tp.bigbluebutton;

import java.util.HashMap;
import java.util.Map;

import com.ambasadoro.Ambasadoro;
import com.ambasadoro.engine.IEngine;
import com.ambasadoro.engine.VendorCodes;

import org.ambasadoro.lti.ILTIConstants;
import org.ambasadoro.lti.IToolProvider;
import org.ambasadoro.lti.v1_0.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class BigBlueButtonEngine implements IEngine{
    public static final String TP_VENDOR_CODE = VendorCodes.TP_CODE_TEST;
    public static final String TP_VENDOR_NAME = "BigBlueButton";
    public static final String TP_VENDOR_DESCRIPTION = "Open source web conferencing system for distance learning.";
    public static final String TP_VENDOR_URL = "http://www.bigbluebutton.org/";
    public static final String TP_VENDOR_CONTACT_EMAIL = "bigbluebutton-users@googlegroups.com";

    Ambasadoro ambasadoro;

    ILTIConstants ltiConstants = new LTIConstants();
    IToolProvider toolProvider;
    Map<String, String> properties = new HashMap<String, String>();
    JSONObject tpMeta;
    JSONObject tcMeta;
    
    public BigBlueButtonEngine(Ambasadoro ambasadoro, Map<String, String> params, String endpoint){
        this.ambasadoro = ambasadoro;

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

    public JSONArray getJSONProperties(){
        return tpMeta.getJSONArray("properties");
    }

    public JSONArray getJSONRequiredParameters(){
        return tpMeta.getJSONArray("requiredParameters");
    }

    public JSONArray getJSONOverride(){
        return tcMeta.getJSONArray("override");
    }
}
