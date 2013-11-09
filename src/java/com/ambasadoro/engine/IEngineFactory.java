package com.ambasadoro.engine;

import java.util.Map;

public interface IEngineFactory {

    public IEngine createEngine(String type, Map<String, String> params);

}
