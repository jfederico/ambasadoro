package com.ambasadoro.engine.tp.test;

import java.util.Map;

import com.ambasadoro.Ambasadoro;
import com.ambasadoro.engine.EngineBase;
import com.ambasadoro.engine.VendorCodes;

import org.ambasadoro.lti.v1_0.*;

public class TestEngine extends EngineBase {
    public static final String TP_VENDOR_CODE = VendorCodes.TP_CODE_TEST;
    public static final String TP_VENDOR_NAME = "Test";
    public static final String TP_VENDOR_DESCRIPTION = "Tool provider for testing purposes.";
    public static final String TP_VENDOR_URL = "http://test.123it.ca/";
    public static final String TP_VENDOR_CONTACT_EMAIL = "support@123it.ca";

    public TestEngine(Ambasadoro ambasadoro, Map<String, String> params, String endpoint) throws Exception {
        super(ambasadoro);
        try {
            super.toolProvider = new ToolProvider(params);
            if( !super.toolProvider.hasValidSignature(endpoint, ambasadoro.getLtiSecret()) )
                throw new Exception("OAuth signature is NOT valid");
            else
                System.out.println("OAuth signature is valid");

            super.toolProvider.overrideParameters(getJSONOverride());
            if( !super.toolProvider.hasRequiredParameters(getJSONRequiredParameters()) )
                throw new Exception("Missing required parameters");
            else
                System.out.println("All required parameters are included");
            

        } catch( Exception e) {
            throw e;
        }
    }

    public String getToolVendorCode(){
        return TP_VENDOR_CODE;
    }

}
