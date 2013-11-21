package com.ambasadoro.engine;

import org.json.JSONArray;
import org.json.JSONObject;

public interface IEngine {
    ///Configuration
    ///SSO to TP
    ///Outcomes from TP

    //Configuration
    //API to TP, Requester
    //API from TP, Responder
    //GUI for end users (admin, faculty, learner), View, workflow and database

    public String getToolTitle();
    public String getToolDescription();
    public String getToolVendorCode();
    public JSONObject getJSONProperties();
    public JSONArray getJSONRequiredParameters();
    public JSONObject getJSONOverride();

}
