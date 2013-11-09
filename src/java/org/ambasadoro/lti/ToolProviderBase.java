package org.ambasadoro.lti;

public class ToolProviderBase implements IToolProvider {

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
