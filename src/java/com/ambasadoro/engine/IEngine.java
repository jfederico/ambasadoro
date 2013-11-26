package com.ambasadoro.engine;

import java.util.Map;

import org.ambasadoro.lti.ILTIConstants;
import org.ambasadoro.lti.IToolProvider;
import org.json.JSONArray;

public interface IEngine {

    public String getToolTitle();
    public String getToolDescription();
    public String getToolVendorCode();
    public JSONArray getJSONProperties();
    public JSONArray getJSONRequiredParameters();
    public JSONArray getJSONExtraParameters();
    public JSONArray getJSONOverride();
    public IToolProvider getToolProvider();
    public ILTIConstants getLTIConstants();
    public Map<String, String> getParameters();
    public String getParameter(String key);
    public void putParameter(String key, String value);
}
