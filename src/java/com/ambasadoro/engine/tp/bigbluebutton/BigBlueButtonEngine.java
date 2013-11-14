package com.ambasadoro.engine.tp.bigbluebutton;

import java.util.Map;

import com.ambasadoro.Ambasadoro;
import com.ambasadoro.engine.IEngine;
import com.ambasadoro.engine.VendorCodes;

public class BigBlueButtonEngine implements IEngine{
    public static final String TP_NAME = "BigBlueButton";
    public static final String TP_DESCRIPTION = "Open source web conferencing system for distance learning.";
    public static final String TP_URL = "http://www.bigbluebutton.org/";
    public static final String TP_CONTACT_EMAIL = "bigbluebutton-users@googlegroups.com";

    String code = VendorCodes.TP_CODE_BIGBLUEBUTTON;
    
    public BigBlueButtonEngine(Ambasadoro ambasadoro, Map<String, String> params, String endpoint){
        
    }
    
    public String getCode(){
        return this.code;
    }
}
