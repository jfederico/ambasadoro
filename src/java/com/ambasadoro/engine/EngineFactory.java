package com.ambasadoro.engine;

import com.ambasadoro.engine.tp.bigbluebutton.BigBlueButtonEngine;
import com.ambasadoro.engine.tp.test.TestEngine;

import java.util.Map;

public class EngineFactory implements IEngineFactory {

    public EngineFactory(){
        
    }
    
    public IEngine createEngine(String code, Map<String, String> params){
        IEngine toolProviderEngine = null;
        
        System.out.println(code);
        
        if(VendorCodes.TP_CODE_TEST.equals(code) ){
            toolProviderEngine = new TestEngine(params);
        } else if(VendorCodes.TP_CODE_BIGBLUEBUTTON.equals(code) ){
            toolProviderEngine = new BigBlueButtonEngine(params);
        } else if(VendorCodes.TP_CODE_CHALKANDWIRE.equals(code) ){
            //toolProviderEngine = new CWEPortfolioEngine(params);
        } else if(VendorCodes.TP_CODE_LIMESURVEY.equals(code) ){
            //toolProviderEngine = new LimeSurveyEngine(params);
        } else if(VendorCodes.TP_CODE_YOUTUBE.equals(code) ){
            //toolProviderEngine = new YouTubeEngine(params);
        }
        
        return toolProviderEngine;
    }

}
