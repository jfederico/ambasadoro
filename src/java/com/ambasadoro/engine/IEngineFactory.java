package com.ambasadoro.engine;

import java.util.Map;

import com.ambasadoro.Ambasadoro;

public interface IEngineFactory {

    public IEngine createEngine(Ambasadoro ambasadoro, Map<String, String> params, String endpoint) throws Exception;
    public Object getEngineClass(Ambasadoro ambasadoro) throws Exception;
}
