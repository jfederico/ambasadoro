package com.ambasadoro.engine.tp.bigbluebutton;

import java.util.Map;

import com.ambasadoro.engine.IEngine;
import com.ambasadoro.engine.VendorCodes;

public class BigBlueButtonEngine implements IEngine{
    
    String code = VendorCodes.TP_CODE_BIGBLUEBUTTON;
    
    public BigBlueButtonEngine(Map<String, String> params){
        
    }
    
    public String getCode(){
        return this.code;
    }
}