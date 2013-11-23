package com.ambasadoro.engine;

import com.ambasadoro.Ambasadoro;
import com.ambasadoro.engine.IEngine;

import org.ambasadoro.lti.ILTIConstants;
import org.ambasadoro.lti.IToolProvider;
import org.ambasadoro.lti.v1_0.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class EngineBase implements IEngine {

    protected Ambasadoro ambasadoro;

    protected ILTIConstants ltiConstants = new LTIConstants();
    protected IToolProvider toolProvider;
    protected JSONObject tpMeta;
    protected JSONObject tcMeta;
    
    public EngineBase(Ambasadoro ambasadoro) throws Exception {
        this.ambasadoro = ambasadoro;
        try {
            this.tpMeta = new JSONObject(ambasadoro.getTpMeta());
            System.out.println(this.tpMeta);
            this.tcMeta = new JSONObject(ambasadoro.getTcMeta());
            System.out.println(this.tcMeta);
        } catch( Exception e) {
            throw e;
        }

    }

    public String getToolTitle(){
        return ambasadoro.getTpTitle();
    }

    public String getToolDescription(){
        return ambasadoro.getTpDescription();
    }

    public String getToolVendorCode(){
        return "";
    }

    public JSONArray getJSONProperties(){
        return tpMeta.getJSONArray("properties");
    }

    public JSONArray getJSONRequiredParameters(){
        return tpMeta.getJSONArray("requiredParameters");
    }

    public JSONArray getJSONOverride(){
        return tcMeta.getJSONArray("overrides");
    }

    public Ambasadoro getAmbasadoro(){
        return this.ambasadoro;
    }

    public ILTIConstants getLTIConstants(){
        return this.ltiConstants;
    }

    public IToolProvider getToolProvider(){
        return this.toolProvider;
    }
}
