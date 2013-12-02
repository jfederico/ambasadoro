package org.bigbluebutton.impl;

import org.bigbluebutton.api.BBBCommand;
import org.bigbluebutton.api.BBBException;
import org.bigbluebutton.api.BBBMeeting;
import org.bigbluebutton.api.BBBProxy;

public class BBBCreateCommand implements BBBCommand {

    BBBProxy proxy;
    BBBMeeting meeting;
    
    public BBBCreateCommand(BBBProxy proxy, BBBMeeting meeting){
        this.proxy = proxy;
        this.meeting = meeting;
    }
    
    @Override
    public void execute() throws BBBException {
        String action = "Creating meeting";
        System.out.println(action);
        BBBProxyImpl.doAPICall(proxy.getCreateURL(meeting));
    }

}
