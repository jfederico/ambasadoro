package com.ambasadoro.engine;

import com.ambasadoro.engine.lti.ToolProviderEngine;
import com.ambasadoro.engine.lti.bigbluebutton.BigBlueButtonEngine;
import com.ambasadoro.lti.Constants;

import java.util.Map;

public class LTIEngineFactory extends EngineFactory {

    public LTIEngineFactory(){
        
    }
    
    public ToolProviderEngine createEngine(String type, Map<String, String> params){
        ToolProviderEngine toolProviderEngine = null;
        
        if(Constants.TP_BIGBLUEBUTTON.equals(type) ){
            toolProviderEngine = new BigBlueButtonEngine(params);
        } else if(Constants.TP_CWEPORTFOLIO.equals(type) ){
            //toolProviderEngine = new CWEPortfolioEngine(params);
        } else if(Constants.TP_LIMESURVEY.equals(type) ){
            //toolProviderEngine = new LimeSurveyEngine(params);
        } else if(Constants.TP_YOUTUBE.equals(type) ){
            //toolProviderEngine = new YouTubeEngine(params);
        }
        
        return toolProviderEngine;
    }

}