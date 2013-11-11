package com.ambasadoro

import java.util.Properties;

import net.oauth.OAuth
import net.oauth.OAuthMessage
import net.oauth.signature.OAuthSignatureMethod
import net.oauth.signature.HMAC_SHA1

import com.ambasadoro.Ambasadoro;
import com.ambasadoro.AmbasadoroService;
import com.ambasadoro.engine.EngineFactory
import com.ambasadoro.engine.IEngine
import com.ambasadoro.engine.IEngineFactory
import com.ambasadoro.engine.VendorCodes;

import net.oauth.OAuth;

class LtiController {

	AmbasadoroService ambasadoroService
	
    IEngineFactory ltiEngineFactory = new EngineFactory()
    
    def index() { 
        log.debug "###############index###############"
		ambasadoroService.logParameters(params)
	}
	
	def tool() {
        log.debug "###############tool###############"
		ambasadoroService.logParameters(params)

        log.debug hasValidSignature(params)

        try {
            log.debug "  - Look for the corresponding Ambasadoro instance"
            Ambasadoro ambasadoro = ambasadoroService.getAmbasadoroInstance(params)
            log.debug "  - Initializing ltiEngine"
            IEngine ltiEngine = ltiEngineFactory.createEngine(ambasadoro, params)
            log.debug "  - Initialized ltiEngine. code [" + ltiEngine.getCode() + "]"
        } catch(Exception e) {
            log.debug "  - Exception: " + e.getMessage()
        }
	}
	
	def toolCartridge() {
        log.debug "###############toolCartridge###############"
		ambasadoroService.logParameters(params)
	}
	
	def toolUi() {
        log.debug "###############toolUi###############"
		ambasadoroService.logParameters(params)
	}
    
    
    private boolean hasValidSignature(Object params) {
        boolean validSignature = false

        if (params.containsKey(OAuth.OAUTH_SIGNATURE)) {
            String signature = params.get(OAuth.OAUTH_SIGNATURE)
            String method = request.getMethod().toUpperCase()
            String URL = "http://192.168.44.138:8888/ambasadoro/lti/tool/1"
            String conSecret = "123"
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

    private Properties sanitizePrametersForBaseString(Object params) {
        Properties reqProp = new Properties();
        for (String key : params.keySet()) {
            if (key == "action" || key == "controller") {
                // Ignore as these are the grails controller and action tied to this request.
                continue
            } else if (key == "oauth_signature") {
                // We don't need this as part of the base string
                continue
            }

            reqProp.setProperty(key, params.get(key));
        }

        return reqProp
    }
}
