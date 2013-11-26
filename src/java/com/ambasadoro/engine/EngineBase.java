package com.ambasadoro.engine;

import java.util.Map;

import com.ambasadoro.Ambasadoro;
import com.ambasadoro.engine.IEngine;
import com.ambasadoro.exceptions.AmbasadoroException;

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
    
    public EngineBase(Ambasadoro ambasadoro, Map<String, String> params, String endpoint) throws AmbasadoroException, Exception {
        this.ambasadoro = ambasadoro;
        try {
            this.tpMeta = new JSONObject(ambasadoro.getTpMeta());
            System.out.println(this.tpMeta);
            this.tcMeta = new JSONObject(ambasadoro.getTcMeta());
            System.out.println(this.tcMeta);

            this.toolProvider = new ToolProvider(params);
            if( !this.toolProvider.hasValidSignature(endpoint, ambasadoro.getLtiSecret()) )
                throw new AmbasadoroException("OAuth signature is NOT valid", "OAuthError" );
            else
                System.out.println("OAuth signature is valid");

            this.toolProvider.overrideParameters(getJSONOverride());
            if( !this.toolProvider.hasRequiredParameters(getJSONRequiredParameters()) )
                throw new AmbasadoroException("Missing required parameters", "OAuthError");
            else
                System.out.println("All required parameters are included");

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

    public JSONArray getJSONExtraParameters(){
        return tpMeta.getJSONArray("extraParameters");
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
    
    public Map<String, String> getParameters(){
        return this.toolProvider.getParameters();
    }

    public String getParameter(String key){
        return this.toolProvider.getParameter(key);
    }

    public void putParameter(String key, String value){
        this.toolProvider.putParameter(key, value);
    }

}
