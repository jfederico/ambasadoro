package com.ambasadoro.engine.tp.bigbluebutton;

import java.util.Map;
import java.util.HashMap;

import com.ambasadoro.Ambasadoro;
import com.ambasadoro.engine.EngineBase;
import com.ambasadoro.engine.VendorCodes;

import org.bigbluebutton.api.BBBCommand;
import org.bigbluebutton.api.BBBException;
import org.bigbluebutton.api.BBBStore;
import org.bigbluebutton.api.BBBProxy;
import org.bigbluebutton.impl.BBBStoreImpl;
import org.bigbluebutton.impl.BBBCreateCommand;

public class BigBlueButtonEngine extends EngineBase{
    public static final String TP_VENDOR_CODE = VendorCodes.TP_CODE_BIGBLUEBUTTON;
    public static final String TP_VENDOR_NAME = "BigBlueButton";
    public static final String TP_VENDOR_DESCRIPTION = "Open source web conferencing system for distance learning.";
    public static final String TP_VENDOR_URL = "http://www.bigbluebutton.org/";
    public static final String TP_VENDOR_CONTACT_EMAIL = "bigbluebutton-users@googlegroups.com";

    BBBStore bbbStore = BBBStoreImpl.getInstance();
    BBBProxy bbbProxy;

    public BigBlueButtonEngine(Ambasadoro ambasadoro, Map<String, String> params, String endpoint) throws Exception {
        super(ambasadoro, params, endpoint);
        bbbProxy = bbbStore.createProxy(ambasadoro.getTpEndpoint(), ambasadoro.getTpSecret());
    }

    public String getToolVendorCode(){
        return TP_VENDOR_CODE;
    }

    //Command implementation for SSO
    public String getSSOURL() throws Exception {
        String ssoURL;
        try{
            BBBCommand createCommand = new BBBCreateCommand(bbbProxy, meetingParams());
            createCommand.execute();
            ssoURL = bbbProxy.getJoinURL(sessionParams());
        } catch ( BBBException e){
            throw new Exception("Error executing SSO", e.getCause());
        }
        return ssoURL;
    }

    private Map<String, String> meetingParams(){
        Map<String, String> params = toolProvider.getParameters();
        Map<String, String> meetingParams = new HashMap<String, String>();
        // Map ToolProvider parameters with Meeting parameters
        meetingParams.put("name", "Demo Meeting");
        meetingParams.put("meetingID", "A342344623445624");
        meetingParams.put("attendeePW", "ap");
        meetingParams.put("moderatorPW", "mp");
        
        return meetingParams;
    }

    private Map<String, String> sessionParams(){
        Map<String, String> params = toolProvider.getParameters();
        Map<String, String> sessionParams = new HashMap<String, String>();
        // Map LtiUser parameters with Session parameters
        sessionParams.put("fullName", "John Doe");
        sessionParams.put("meetingID", "A342344623445624");
        sessionParams.put("password", "mp");
        //sessionParams.put("createTime", "");
        //sessionParams.put("userID", "");

        return sessionParams;
    }

}
