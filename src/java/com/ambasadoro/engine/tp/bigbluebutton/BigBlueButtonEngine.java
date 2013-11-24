package com.ambasadoro.engine.tp.bigbluebutton;

import java.util.Map;

import com.ambasadoro.Ambasadoro;
import com.ambasadoro.engine.EngineBase;
import com.ambasadoro.engine.VendorCodes;

public class BigBlueButtonEngine extends EngineBase{
    public static final String TP_VENDOR_CODE = VendorCodes.TP_CODE_TEST;
    public static final String TP_VENDOR_NAME = "BigBlueButton";
    public static final String TP_VENDOR_DESCRIPTION = "Open source web conferencing system for distance learning.";
    public static final String TP_VENDOR_URL = "http://www.bigbluebutton.org/";
    public static final String TP_VENDOR_CONTACT_EMAIL = "bigbluebutton-users@googlegroups.com";

    public BigBlueButtonEngine(Ambasadoro ambasadoro, Map<String, String> params, String endpoint) throws Exception {
        super(ambasadoro, params, endpoint);
    }

    public String getToolVendorCode(){
        return TP_VENDOR_CODE;
    }
}
