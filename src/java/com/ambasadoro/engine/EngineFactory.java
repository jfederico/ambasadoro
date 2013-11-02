package com.ambasadoro.engine;

import java.util.Map;

import com.ambasadoro.engine.lti.ToolProviderEngine;

public abstract class EngineFactory {

    public abstract ToolProviderEngine createEngine(String type, Map<String, String> params);

}
