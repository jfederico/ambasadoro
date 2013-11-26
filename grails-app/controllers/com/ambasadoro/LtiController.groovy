package com.ambasadoro

import java.util.Properties;

import net.oauth.OAuth

import net.oauth.OAuthMessage
import net.oauth.signature.OAuthSignatureMethod
import net.oauth.signature.HMAC_SHA1

import com.ambasadoro.Ambasadoro
import com.ambasadoro.AmbasadoroService
import com.ambasadoro.engine.EngineFactory
import com.ambasadoro.engine.IEngine
import com.ambasadoro.engine.IEngineFactory
import com.ambasadoro.engine.VendorCodes
import com.ambasadoro.exceptions.AmbasadoroException

import org.ambasadoro.lti.IToolProvider
import org.ambasadoro.lti.Roles

import net.oauth.OAuth;

class LtiController {

	AmbasadoroService ambasadoroService
	
    IEngineFactory ltiEngineFactory = EngineFactory.getInstance()
    
    def index() { 
        log.debug "###############index###############"
		ambasadoroService.logParameters(params)
	}
	
	def tool() {
        log.debug "###############tool###############"
		ambasadoroService.logParameters(params)

        try {
            log.debug "  - Look for the corresponding Ambasadoro instance"
            Ambasadoro ambasadoro = ambasadoroService.getAmbasadoroInstance(params)
            String endpoint = ambasadoroService.retrieveEndpoint(request.isSecure()?"https":"http") + "/lti/tool/" + params.get("id")

            log.debug "  - Initializing ltiEngine"
            ambasadoroService.sanitizePrameters(params)
            IEngine engine = ltiEngineFactory.createEngine(ambasadoro, params, endpoint)
            log.debug "  - Initialized ltiEngine. code [" + engine.getToolVendorCode() + "]"
            
            log.debug "  - Parameters after override"
            ambasadoroService.logParameters(engine.getParameters())
            
            LtiLaunch ltiLaunch = ambasadoroService.saveLtiLaunch(engine.getLTIConstants(), ambasadoro, engine.getParameters())
            
            
            if( !ambasadoroService.hasAllExtraParameterSet(engine, ltiLaunch) ){
                if( !Roles.isLearner(engine.getParameter("roles")) ) {
                    ///Present interface for setting up extraParameters   
                    log.debug "<<<< Present interface for setting up extraParameters >>>>"
                } else {
                    ///Present error message telling learners that extraParameters are not set yet
                    log.debug "<<<< Present error message telling learners that extraParameters are not set yet >>>>"
                }
            } else {
                ///Go for the launch
                log.debug "<<<< Go for the launch >>>>"
                //def launchingURL = engine.getLaunchingURL()
                //redirect(url:launchingURL)
            }

                
        } catch(AmbasadoroException e) {
            log.debug "  - AmbasadoroException: " + e.getErrorCode() + ":" + e.getLocalizedMessage()
            render(view: "error", model: ['resultMessageKey': e.getErrorCode(), 'resultMessage': e.getLocalizedMessage()])
        } catch(Exception e) {
            log.debug "  - Exception: " + e.getLocalizedMessage()
            render(view: "error", model: ['resultMessageKey': 'GeneralError', 'resultMessage': e.getLocalizedMessage()])
        }
	}
	
	def toolCartridge() {
        log.debug "###############toolCartridge###############"
		ambasadoroService.logParameters(params)
        try {
            log.debug "  - Look for the corresponding Ambasadoro instance"
            Ambasadoro ambasadoro = ambasadoroService.getAmbasadoroInstance(params)
            Object engineClass = ltiEngineFactory.getEngineClass(ambasadoro)
            render(text: getCartridgeXML(ambasadoro, engineClass), contentType: "text/xml", encoding: "UTF-8")
        } catch(Exception e) {
            log.debug "  - Exception: " + e.getMessage()
        }
	}
	
	def toolUi() {
        log.debug "###############toolUi###############"
		ambasadoroService.logParameters(params)
	}
    
    private String getCartridgeXML(Ambasadoro ambasadoro, Object engineClass){
        def cartridge = '' +
        '<?xml version="1.0" encoding="UTF-8"?>' +
        '<cartridge_basiclti_link xmlns="http://www.imsglobal.org/xsd/imslticc_v1p0"' +
        '       xmlns:blti = "http://www.imsglobal.org/xsd/imsbasiclti_v1p0"' +
        '       xmlns:lticm ="http://www.imsglobal.org/xsd/imslticm_v1p0"' +
        '       xmlns:lticp ="http://www.imsglobal.org/xsd/imslticp_v1p0"' +
        '       xmlns:xsi = "http://www.w3.org/2001/XMLSchema-instance"' +
        '       xsi:schemaLocation = "http://www.imsglobal.org/xsd/imslticc_v1p0 http://www.imsglobal.org/xsd/lti/ltiv1p0/imslticc_v1p0.xsd' +
        '                             http://www.imsglobal.org/xsd/imsbasiclti_v1p0 http://www.imsglobal.org/xsd/lti/ltiv1p0/imsbasiclti_v1p0.xsd' +
        '                             http://www.imsglobal.org/xsd/imslticm_v1p0 http://www.imsglobal.org/xsd/lti/ltiv1p0/imslticm_v1p0.xsd' +
        '                             http://www.imsglobal.org/xsd/imslticp_v1p0 http://www.imsglobal.org/xsd/lti/ltiv1p0/imslticp_v1p0.xsd">' +
        '    <blti:title>' + ambasadoro.getTpTitle() + '</blti:title>' +
        '    <blti:description>' + ambasadoro.getTpDescription() + '</blti:description>' +
        '    <blti:launch_url>' + ambasadoroService.retrieveEndpoint() + '/lti/tool/' + ambasadoro.getId() + '</blti:launch_url>' +
        '    <blti:secure_launch_url>' + ambasadoroService.retrieveEndpoint('https') + '/lti/tool/' + ambasadoro.getId() + '</blti:secure_launch_url>' +
        '    <blti:icon>' + ambasadoroService.retrieveEndpoint() + '/images/' + ambasadoro.getId() + '/favicon.ico</blti:icon>' +
        '    <blti:secure_icon>' + ambasadoroService.retrieveEndpoint('https') + '/images/' + ambasadoro.getId() + '/favicon.ico</blti:secure_icon>' +
        '    <blti:vendor>' +
        '        <lticp:code>' + ambasadoro.getTpVendorCode() + '</lticp:code>' +
        '        <lticp:name>' + engineClass.TP_VENDOR_NAME + '</lticp:name>' +
        '        <lticp:description>' + engineClass.TP_VENDOR_DESCRIPTION + '</lticp:description>' +
        '        <lticp:url>' + engineClass.TP_VENDOR_URL + '</lticp:url>' +
        '        <lticp:contact>' +
        '            <lticp:email>' + engineClass.TP_VENDOR_CONTACT_EMAIL + '</lticp:email>' +
        '        </lticp:contact>' +
        '    </blti:vendor>' +
        '    <cartridge_bundle identifierref="BLTI001_Bundle"/>' +
        '    <cartridge_icon identifierref="BLTI001_Icon"/>' +
        '</cartridge_basiclti_link>'

        return cartridge
    }
}
