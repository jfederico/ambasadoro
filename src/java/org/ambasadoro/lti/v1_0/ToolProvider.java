package org.ambasadoro.lti.v1_0;

import java.util.Map;

import org.ambasadoro.lti.ToolProviderBase;

public class ToolProvider extends ToolProviderBase{

    public ToolProvider(Map<String, String> params) throws Exception{
        super(params);
        System.out.println("ToolProvider v1.0 initializad");
    }
}
