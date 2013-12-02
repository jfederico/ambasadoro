package org.bigbluebutton.impl;

import java.util.Map;

import org.bigbluebutton.api.BBBCommand;
import org.bigbluebutton.api.BBBException;
import org.bigbluebutton.api.BBBProxy;

public class BBBCreateCommand implements BBBCommand {

    BBBProxy proxy;
    Map<String, String> params;
    
    public BBBCreateCommand(BBBProxy proxy, Map<String, String> params){
        this.proxy = proxy;
        this.params = params;
    }
    
    @Override
    public void execute() throws BBBException {
        String action = "Creating meeting";
        System.out.println(action);
        BBBProxyImpl.doAPICall(proxy.getCreateURL(params));
    }

}
