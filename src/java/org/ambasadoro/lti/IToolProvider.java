package org.ambasadoro.lti;

import java.util.Map;

import org.json.JSONArray;

public interface IToolProvider {

    public boolean hasValidSignature(String url, String sharedSecret) throws Exception;
    public boolean hasRequiredParameters(JSONArray requiredParameters) throws Exception;
    public void overrideParameters(JSONArray overrides) throws Exception;
    public Map<String, String> getParameters();
    public String getParameter(String key);
    public void putParameter(String key, String value);
}
