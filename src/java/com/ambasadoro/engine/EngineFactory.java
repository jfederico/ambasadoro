package com.ambasadoro.engine;

import com.ambasadoro.Ambasadoro;
import com.ambasadoro.engine.tp.bigbluebutton.BigBlueButtonEngine;
import com.ambasadoro.engine.tp.test.TestEngine;

import java.util.Map;

public class EngineFactory implements IEngineFactory {

    public EngineFactory(){
        
    }
    
    public IEngine createEngine(Ambasadoro ambasadoro, Map<String, String> params){
        IEngine toolProviderEngine = null;
        
        System.out.println(ambasadoro.toolCode);
        
        if(VendorCodes.TP_CODE_TEST.equals(ambasadoro.toolCode) ){
            toolProviderEngine = new TestEngine(ambasadoro, params);
        } else if(VendorCodes.TP_CODE_BIGBLUEBUTTON.equals(ambasadoro.toolCode) ){
            toolProviderEngine = new BigBlueButtonEngine(ambasadoro, params);
        } else if(VendorCodes.TP_CODE_CHALKANDWIRE.equals(ambasadoro.toolCode) ){
            //toolProviderEngine = new CWEPortfolioEngine(ambasadoro, params);
        } else if(VendorCodes.TP_CODE_LIMESURVEY.equals(ambasadoro.toolCode) ){
            //toolProviderEngine = new LimeSurveyEngine(ambasadoro, params);
        } else if(VendorCodes.TP_CODE_YOUTUBE.equals(ambasadoro.toolCode) ){
            //toolProviderEngine = new YouTubeEngine(ambasadoro, params);
        }
        
        return toolProviderEngine;
    }

}
