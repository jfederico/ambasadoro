package com.ambasadoro.engine;

import com.ambasadoro.Ambasadoro;
import com.ambasadoro.engine.tp.bigbluebutton.BigBlueButtonEngine;
import com.ambasadoro.engine.tp.test.TestEngine;

import java.util.Map;

public class EngineFactory implements IEngineFactory {

    public EngineFactory(){
        
    }
    
    public IEngine createEngine(Ambasadoro ambasadoro, Map<String, String> params) throws Exception {
        IEngine toolProviderEngine = null;
        String toolCode = ambasadoro.getToolCode();
        
        try {
            if(VendorCodes.TP_CODE_TEST.equals(toolCode) ){
                toolProviderEngine = new TestEngine(ambasadoro, params);
            } else if(VendorCodes.TP_CODE_BIGBLUEBUTTON.equals(toolCode) ){
                toolProviderEngine = new BigBlueButtonEngine(ambasadoro, params);
            } else if(VendorCodes.TP_CODE_CHALKANDWIRE.equals(toolCode) ){
                //toolProviderEngine = new CWEPortfolioEngine(ambasadoro, params);
            } else if(VendorCodes.TP_CODE_LIMESURVEY.equals(toolCode) ){
                //toolProviderEngine = new LimeSurveyEngine(ambasadoro, params);
            } else if(VendorCodes.TP_CODE_YOUTUBE.equals(toolCode) ){
                //toolProviderEngine = new YouTubeEngine(ambasadoro, params);
            }
        } catch ( Exception e ){
            throw e;
        }
        
        return toolProviderEngine;
    }

}
