package com.ambasadoro.engine;

import java.util.Map;

import org.lti.api.LTIToolProvider;
import org.json.JSONArray;

public interface IEngine {

    public String getToolTitle();
    public String getToolDescription();
    public String getToolVendorCode();
    public JSONArray getJSONProperties();
    public JSONArray getJSONRequiredParameters();
    public boolean hasExtraParameters();
    public JSONArray getJSONExtraParameters();
    public JSONArray getJSONOverride();
    public LTIToolProvider getToolProvider();
    public Map<String, String> getParameters();
    public String getParameter(String key);
    public void putParameter(String key, String value);
    // Command implementation
    // for SSO
    public String getSSOURL() throws Exception;
}
