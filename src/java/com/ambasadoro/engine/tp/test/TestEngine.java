package com.ambasadoro.engine.tp.test;

import java.util.Map;

import com.ambasadoro.Ambasadoro;
import com.ambasadoro.engine.IEngine;
import com.ambasadoro.engine.VendorCodes;

import org.ambasadoro.lti.ILTIConstants;
import org.ambasadoro.lti.IToolProvider;
import org.ambasadoro.lti.v1_0.*;

public class TestEngine implements IEngine {
    
    String code = VendorCodes.TP_CODE_TEST;
    
    ILTIConstants ltiConstants = new LTIConstants();
    IToolProvider toolProvider;

    public TestEngine(Ambasadoro ambasadoro, Map<String, String> params){
        System.out.println("Creating ltiEngine for [" + code + "]");
        toolProvider = new ToolProvider(params);
    }
    
    public String getCode(){
        return this.code;
    }
    
}
