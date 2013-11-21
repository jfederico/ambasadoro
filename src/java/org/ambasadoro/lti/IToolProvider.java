package org.ambasadoro.lti;

import org.json.JSONArray;

public interface IToolProvider {

    public boolean hasValidSignature(String url, String sharedSecret) throws Exception;
    public boolean hasRequiredParameters(JSONArray requiredParameters) throws Exception;

}
