package com.ambasadoro.engine.tp.bigbluebutton;

import java.util.Map;

import com.ambasadoro.Ambasadoro;
import com.ambasadoro.engine.IEngine;
import com.ambasadoro.engine.VendorCodes;

public class BigBlueButtonEngine implements IEngine{
    public static final String TP_VENDOR_CODE = VendorCodes.TP_CODE_TEST;
    public static final String TP_VENDOR_NAME = "BigBlueButton";
    public static final String TP_VENDOR_DESCRIPTION = "Open source web conferencing system for distance learning.";
    public static final String TP_VENDOR_URL = "http://www.bigbluebutton.org/";
    public static final String TP_VENDOR_CONTACT_EMAIL = "bigbluebutton-users@googlegroups.com";

    Ambasadoro ambasadoro;

    String code = VendorCodes.TP_CODE_BIGBLUEBUTTON;
    
    public BigBlueButtonEngine(Ambasadoro ambasadoro, Map<String, String> params, String endpoint){
        this.ambasadoro = ambasadoro;

    }
    
    public String getToolTitle(){
        return ambasadoro.getToolTitle();
    }

    public String getToolDescription(){
        return ambasadoro.getToolDescription();
    }

    public String getToolVendorCode(){
        return TP_VENDOR_CODE;
    }

    public boolean hasAllRequiredParams(){
        return false;
    }
    
}
