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
package org.bigbluebutton.api;

public interface BBBProxy {

    // API Server Path
    public final static String API_SERVERPATH = "api/";

    // API Calls
    public final static String APICALL_CREATE            = "create";
    public final static String APICALL_JOIN              = "join";
    public final static String APICALL_ISMEETINGRUNNING  = "isMeetingRunning";
    public final static String APICALL_END               = "end";
    public final static String APICALL_GETMEETINGINFO    = "getMeetingInfo";
    public final static String APICALL_GETMEETINGS       = "getMeetings";
    public final static String APICALL_GETRECORDINGS     = "getRecordings";
    public final static String APICALL_PUBLISHRECORDINGS = "publishRecordings";
    public final static String APICALL_DELETERECORDINGS  = "deleteRecordings";

    // API Response Codes
    public final static String APIRESPONSE_SUCCESS = "SUCCESS";
    public final static String APIRESPONSE_FAILED = "FAILED";

    // API MesageKey Codes
    public final static String MESSAGEKEY_IDNOTUNIQUE = "idNotUnique";
    public final static String MESSAGEKEY_DUPLICATEWARNING = "duplicateWarning";

    public final static String PARAMETERENCODING = "UTF-8";

    public String getVersionURL();
    public String getCreateURL(String name, String meetingID, String attendeePW, String moderatorPW, String welcome, String dialNumber, String voiceBridge, String webVoice, String logoutURL, String maxParticipants, String record, String duration, String meta );
    public String getJoinURL(String fullName, String meetingID, String password, String createTime, String userID);
    public String getJoinURL(String fullName, String meetingID, String password, String createTime, String userID, String webVoiceConf );
    public String getIsMeetingRunningURL(String meetingID);
    public String getEndURL(String meetingID, String password);
    public String getGetMeetingInfoURL(String meetingID, String password);
    public String getGetMeetingsURL(String meetingID, String password);
    public String getStringEncoded(String string);
    public String getGetRecordingsURL(String meetingID);
    public String getPublishRecordingsURL(String recordID, boolean publish);
    public String getDeleteRecordingsURL(String recordID);
}
