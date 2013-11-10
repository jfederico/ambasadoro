package com.ambasadoro

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
	
}
