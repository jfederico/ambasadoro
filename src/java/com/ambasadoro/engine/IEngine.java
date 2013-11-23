package com.ambasadoro.engine;

import org.ambasadoro.lti.IToolProvider;
import org.json.JSONArray;

public interface IEngine {

    public String getToolTitle();
    public String getToolDescription();
    public String getToolVendorCode();
    public JSONArray getJSONProperties();
    public JSONArray getJSONRequiredParameters();
    public JSONArray getJSONOverride();
    public IToolProvider getToolProvider();

}
