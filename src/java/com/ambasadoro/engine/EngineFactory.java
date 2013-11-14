package com.ambasadoro.engine;

import java.util.Map;

import com.ambasadoro.Ambasadoro;
import com.ambasadoro.engine.tp.bigbluebutton.BigBlueButtonEngine;
import com.ambasadoro.engine.tp.test.TestEngine;

public class EngineFactory implements IEngineFactory {

    public EngineFactory(){
        
    }
    
    public IEngine createEngine(Ambasadoro ambasadoro, Map<String, String> params, String endpoint) throws Exception {
        IEngine toolProviderEngine = null;
        String toolCode = ambasadoro.getToolCode();
        
        try {
            if(VendorCodes.TP_CODE_TEST.equals(toolCode) ){
                toolProviderEngine = new TestEngine(ambasadoro, params, endpoint);
            } else if(VendorCodes.TP_CODE_BIGBLUEBUTTON.equals(toolCode) ){
                toolProviderEngine = new BigBlueButtonEngine(ambasadoro, params, endpoint);
            } else if(VendorCodes.TP_CODE_CHALKANDWIRE.equals(toolCode) ){
                //toolProviderEngine = new CWEPortfolioEngine(ambasadoro, params, endpoint);
            } else if(VendorCodes.TP_CODE_LIMESURVEY.equals(toolCode) ){
                //toolProviderEngine = new LimeSurveyEngine(ambasadoro, params, endpoint);
            } else if(VendorCodes.TP_CODE_YOUTUBE.equals(toolCode) ){
                //toolProviderEngine = new YouTubeEngine(ambasadoro, params, endpoint);
            }
        } catch ( Exception e ){
            throw e;
        }
        
        return toolProviderEngine;
    }
}
