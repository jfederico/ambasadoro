package org.ambasadoro.lti;

import com.ambasadoro.exceptions.AmbasadoroException;

import java.util.Map;
import java.util.Properties;

import net.oauth.OAuth;
import net.oauth.OAuthMessage;
import net.oauth.signature.HMAC_SHA1;

import org.json.JSONArray;
import org.json.JSONObject;

public class ToolProviderBase implements IToolProvider {
    
    protected Map<String, String> params;
    protected String oauth_consumer_key;
    protected String oauth_nonce;
    protected String oauth_callback;
    protected String oauth_signature;
    protected String oauth_signature_method;
    protected String oauth_version;
    protected String oauth_timestamp;
    
    public ToolProviderBase() {}
    
    public ToolProviderBase(Map<String, String> params) throws AmbasadoroException, Exception {
        System.out.println("ToolProviderBase initializad");
        if( params.containsKey(OAuth.OAUTH_CONSUMER_KEY)) oauth_consumer_key = params.get(OAuth.OAUTH_CONSUMER_KEY); else throw new AmbasadoroException("Parameter [" + OAuth.OAUTH_CONSUMER_KEY + "] not included", "OAuthError");
        if( params.containsKey(OAuth.OAUTH_SIGNATURE)) oauth_signature = params.get(OAuth.OAUTH_SIGNATURE); else throw new AmbasadoroException("Parameter [" + OAuth.OAUTH_SIGNATURE + "] not included", "OAuthError");
        this.params = params;
    }

    public boolean hasValidSignature(String url, String secret) throws AmbasadoroException, Exception {
        boolean response = false;
        System.out.println("Checking if the OAuth signature is valid. url=" + url + ", secret=" + secret );
        Object postProp = sanitizePrametersForBaseString();
        
        OAuthMessage oam = new OAuthMessage("POST", url, ((Properties)postProp).entrySet());
        HMAC_SHA1 hmac = new HMAC_SHA1();
        hmac.setConsumerSecret(secret);
        String baseString = HMAC_SHA1.getBaseString(oam);
        System.out.println("Base Message String = [ " + baseString + " ]\n");
        if( hmac.isValid(oauth_signature, baseString) )
            response = true;
        System.out.println("Calculated: " + hmac.getSignature(baseString) + " Received: " + oauth_signature);

        return response;
    }

    public boolean hasRequiredParameters(JSONArray requiredParameters) throws AmbasadoroException, Exception {
        boolean response = true;
        String missingParams = "";
        for (int i = 0; i < requiredParameters.length(); i++) {
            if( !params.containsKey(requiredParameters.getString(i)) ){
                if( missingParams.length()>0) missingParams += ", ";
                missingParams += requiredParameters.getString(i);
                response = false;
            }
        }
        if(!response) throw new AmbasadoroException("Required Parameters [" + missingParams + "] not included", "ToolProviderError");
        else return response;
    }

    public void overrideParameters(JSONArray overrides) throws Exception {
        JSONObject override;
        String source;
        String target;

        for (int i = 0; i < overrides.length(); i++) {
            override = overrides.getJSONObject(i);
            source = override.getString("source");
            target = override.getString("target");
            if( params.containsKey(target) ){
                params.put(source, params.get(target));
            }
        }
        
    }

    protected Properties sanitizePrametersForBaseString() {
        Properties reqProp = new Properties();
        for (String key : params.keySet()) {
            if (key.equals("oauth_signature") ) {
                // We don't need this as part of the base string
                continue;
            }
            String value = params.get(key);
            reqProp.setProperty(key, value);
        }

        return reqProp;
    }

    public Map<String, String> getParameters(){
        return params;
    }
    
    public String getParameter(String key){
        return params.get(key);
    }

    public void putParameter(String key, String value){
        params.put(key, value);
    }
}
