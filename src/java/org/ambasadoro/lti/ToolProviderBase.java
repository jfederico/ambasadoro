package org.ambasadoro.lti;

import java.util.Map;
import java.util.Properties;

import net.oauth.OAuth;
import net.oauth.OAuthMessage;
import net.oauth.signature.OAuthSignatureMethod;
import net.oauth.signature.HMAC_SHA1;

public class ToolProviderBase implements IToolProvider {
    
    protected Map<String, String> params;
    protected String oauth_consumer_key;
    protected String oauth_nonce;
    protected String oauth_callback;
    protected String oauth_signature;
    protected String oauth_signature_method;
    protected String oauth_version;
    protected String oauth_timestamp;
    
    public ToolProviderBase(){
        
    }
    
    public ToolProviderBase(Map<String, String> params) throws Exception{
        System.out.println("ToolProviderBase initializad");
        if( params.containsKey(OAuth.OAUTH_CONSUMER_KEY)) oauth_consumer_key = params.get(OAuth.OAUTH_CONSUMER_KEY); else throw new Exception("Parameter [" + OAuth.OAUTH_CONSUMER_KEY + "] not included");
        if( params.containsKey(OAuth.OAUTH_SIGNATURE)) oauth_signature = params.get(OAuth.OAUTH_SIGNATURE); else throw new Exception("Parameter [" + OAuth.OAUTH_SIGNATURE + "] not included");
        this.params = params;
    }

    public boolean hasValidSignature(String url, String secret) throws Exception {
        boolean validSignature = false;
        System.out.println("Checking if the OAuth signature is valid. url=" + url + ", secret=" + secret );
        Object postProp = sanitizePrametersForBaseString();
        
        OAuthMessage oam = new OAuthMessage("POST", url, ((Properties)postProp).entrySet());
        HMAC_SHA1 hmac = new HMAC_SHA1();
        hmac.setConsumerSecret(secret);
        String baseString = HMAC_SHA1.getBaseString(oam);
        if( hmac.isValid(oauth_signature, baseString) )
            validSignature = true;
        System.out.println("Calculated: " + hmac.getSignature(baseString) + " Received: " + oauth_signature);

        return validSignature;
    }

    public Properties sanitizePrametersForBaseString() {

        Properties reqProp = new Properties();
        for (String key : params.keySet()) {
            if (key.equals("action") || key.equals("controller") ) {
                // Ignore as these are the grails controller and action tied to this request.
                continue;
            } else if (key.equals("oauth_signature") ) {
                // We don't need this as part of the base string
                continue;
            }
            String value = params.get(key);
            //System.out.println(key + "=" + value);
            reqProp.setProperty(key, value);
        }

        return reqProp;
    }


    /*
    private boolean hasValidSignature(Customer customer, Object params) {
        boolean validSignature = false

        if (params.containsKey(OAuth.OAUTH_SIGNATURE)) {
            String signature = params.get(OAuth.OAUTH_SIGNATURE)
            String method = request.getMethod().toUpperCase()
            String URL = epcServerService.retrieveBasicLtiEndpoint(request.isSecure()?"https":"http")
            String conSecret = customer.sharedSecret
            Object postProp = sanitizePrametersForBaseString(params)
            
            OAuthMessage oam = new OAuthMessage(method, URL, ((Properties)postProp).entrySet())
            HMAC_SHA1 hmac = new HMAC_SHA1()
            hmac.setConsumerSecret(conSecret)

            log.debug("Base Message String = [ " + hmac.getBaseString(oam) + " ]\n")
            String calculatedSignature = hmac.getSignature(hmac.getBaseString(oam))
            log.debug("Calculated: " + calculatedSignature + " Received: " + signature)
            validSignature = calculatedSignature.equals(signature)

        }

        return validSignature
    }
    */

}
