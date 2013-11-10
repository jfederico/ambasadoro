package com.ambasadoro.engine;

import com.ambasadoro.Ambasadoro;
import java.util.Map;

public interface IEngineFactory {

    public IEngine createEngine(Ambasadoro ambasadoro, Map<String, String> params);

}
