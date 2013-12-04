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

import org.apache.log4j.Logger;
import org.bigbluebutton.api.BBBCommand;
import org.bigbluebutton.api.BBBException;
import org.bigbluebutton.api.BBBProxy;

public class BBBDeleteRecordings implements BBBCommand {

    private static final Logger log = Logger.getLogger(BBBDeleteRecordings.class);

    private static final String ACTION = "Deleting recording";

    public BBBDeleteRecordings(BBBProxy proxy, Map<String, String> params){
        this.proxy = proxy;
        this.params = params;
    }

    private String getURL(){
        String url = proxy.getDeleteRecordingsURL(params);
        return url;
    }

    BBBProxy proxy;
    Map<String, String> params;

    @Override
    public void execute() throws BBBException {
        log.info(ACTION);
        String url = getURL();
        log.debug("Executing [" + url + "]");
        BBBProxyImpl.doAPICall(url);
    }
}
