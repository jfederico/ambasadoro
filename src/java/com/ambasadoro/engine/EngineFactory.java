package com.ambasadoro.engine;

import java.util.Map;

import com.ambasadoro.Ambasadoro;
import com.ambasadoro.engine.tp.bigbluebutton.BigBlueButtonEngine;
import com.ambasadoro.engine.tp.test.TestEngine;

public class EngineFactory implements IEngineFactory {

    private static final EngineFactory INSTANCE = new EngineFactory();

    private EngineFactory() {}

    public static EngineFactory getInstance() {
        return INSTANCE;
    }

    public IEngine createEngine(Ambasadoro ambasadoro, Map<String, String> params, String endpoint) throws Exception {
        IEngine toolProviderEngine = null;
        String toolCode = ambasadoro.getTpVendorCode();
        try {
            if(VendorCodes.TP_CODE_TEST.equals(toolCode) ){
                toolProviderEngine = new TestEngine(ambasadoro, params, endpoint);
            } else if(VendorCodes.TP_CODE_BIGBLUEBUTTON.equals(toolCode) ){
                toolProviderEngine = new BigBlueButtonEngine(ambasadoro, params, endpoint);
            } else if(VendorCodes.TP_CODE_CHALKANDWIRE.equals(toolCode) ){
                //toolProviderEngine = new ChalkAndWireEngine(ambasadoro, params, endpoint);
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
    
    public Object getEngineClass(Ambasadoro ambasadoro) throws Exception {
        Object engineClass = null;
        String toolCode = ambasadoro.getTpVendorCode();
        try {
            if(VendorCodes.TP_CODE_TEST.equals(toolCode) ){
                engineClass = TestEngine.class;
            } else if(VendorCodes.TP_CODE_BIGBLUEBUTTON.equals(toolCode) ){
                engineClass = BigBlueButtonEngine.class;
            } else if(VendorCodes.TP_CODE_CHALKANDWIRE.equals(toolCode) ){
                //engineClass = ChalkAndWireEngine.class;
            } else if(VendorCodes.TP_CODE_LIMESURVEY.equals(toolCode) ){
                //engineClass = LimeSurveyEngine.class;
            } else if(VendorCodes.TP_CODE_YOUTUBE.equals(toolCode) ){
                //engineClass = YouTubeEngine.class;
            }
        } catch ( Exception e ){
            throw e;
        }
        
        return engineClass;
    }
}
