/*
    BigBlueButton - http://www.bigbluebutton.org

    Copyright (c) 2008-2012 by respective authors (see below). All rights reserved.

    BigBlueButton is free software; you can redistribute it and/or modify it under the
    terms of the GNU Lesser General Public License as published by the Free Software
    Foundation; either version 2 of the License, or (at your option) any later
    version.

    BigBlueButton is distributed in the hope that it will be useful, but WITHOUT ANY
    WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
    PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License along
    with BigBlueButton; if not, If not, see <http://www.gnu.org/licenses/>.

    Author: Jesus Federico <jesus@blindsidenetworks.com>
*/ 
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
        String createURL = proxy.getCreateURL(params);
        System.out.println("Executing [" + createURL + "]");
        BBBProxyImpl.doAPICall(createURL);
    }

}
