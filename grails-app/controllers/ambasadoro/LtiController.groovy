package ambasadoro

import com.ambasadoro.engine.EngineFactory
import com.ambasadoro.engine.LTIEngineFactory
import com.ambasadoro.engine.ToolProviderEngine
import com.ambasadoro.lti.Constants

import net.oauth.OAuth;

class LtiController {

	AmbasadoroService ambasadoroService
	
    EngineFactory ltiEngineFactory = new LTIEngineFactory()
    
    def index() { 
		ambasadoroService.logParameters(params)
	}
	
	def tool_launch() {
		ambasadoroService.logParameters(params)
        
        /* First test if the request is post and comes with the minimum information, 
           meaning is oauth request. It must have a key */
        
        String lti_key = (String)params.get(OAuth.OAUTH_CONSUMER_KEY);
        
        // It will send the corresponding TP code that it finds in the database (entity table)
        ToolProviderEngine ltiEngine = ltiEngineFactory.createEngine(Constants.TP_BIGBLUEBUTTON, params)
        
        ltiEngine.validate 
	}
	
	def tool_cartridge() {
		ambasadoroService.logParameters(params)
	}
	
	def tool_ui() {
		ambasadoroService.logParameters(params)
	}
	
}
