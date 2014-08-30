package com.ambasadoro.engine.tp.test;

import java.util.Map;

import com.ambasadoro.Ambasadoro;
import com.ambasadoro.engine.EngineBase;
import com.ambasadoro.engine.VendorCodes;

public class TestEngine extends EngineBase {
    public static final String TP_VENDOR_CODE = VendorCodes.TP_CODE_TEST;
    public static final String TP_VENDOR_NAME = "Test";
    public static final String TP_VENDOR_DESCRIPTION = "Ambasadoro Tool provider for unit test.";
    public static final String TP_VENDOR_URL = "http://www.ambasadoro.com/";
    public static final String TP_VENDOR_CONTACT_EMAIL = "support@ambasadoro.com";

    public TestEngine(Ambasadoro ambasadoro, Map<String, String> params, String endpoint) throws Exception {
        super(ambasadoro, params, endpoint);
    }

    public String getToolVendorCode(){
        return TP_VENDOR_CODE;
    }

}
