package com.ambasadoro.engine;

import java.util.Map;

import com.ambasadoro.Ambasadoro;
import com.ambasadoro.engine.IEngine;
import com.ambasadoro.exceptions.AmbasadoroException;

import org.apache.log4j.Logger;
import org.lti.api.LTIToolProvider;
import org.lti.api.LTIStore;
import org.lti.impl.LTIStoreImpl;
import org.json.JSONArray;
import org.json.JSONObject;

public class EngineBase implements IEngine {

    private static final Logger log = Logger.getLogger(EngineBase.class);
    
    protected Ambasadoro ambasadoro;

    //protected IToolProvider toolProvider;
    protected LTIStore ltiStore;
    protected LTIToolProvider tp;
    protected JSONObject tpMeta;
    protected JSONObject tcMeta;
    
    public EngineBase(Ambasadoro ambasadoro, Map<String, String> params, String endpoint) throws AmbasadoroException, Exception {
        this.ambasadoro = ambasadoro;
        try {
            this.tpMeta = new JSONObject(ambasadoro.getTpMeta());
            log.debug("tpMeta = " + this.tpMeta);
            this.tcMeta = new JSONObject(ambasadoro.getTcMeta());
            log.debug("tcMeta = " + this.tcMeta);

            ltiStore = LTIStoreImpl.getInstance();
            this.tp = ltiStore.createToolProvider(endpoint, ambasadoro.getLtiKey(), ambasadoro.getLtiSecret(), params, "1.0");
            if( !this.tp.hasValidSignature() )
                throw new AmbasadoroException("OAuth signature is NOT valid", "OAuthError" );
            else
                log.debug("OAuth signature is valid");
            
            this.tp.overrideParameters(getJSONOverride());
            if( !this.tp.hasRequiredParameters(getJSONRequiredParameters()) )
                throw new AmbasadoroException("Missing required parameters", "OAuthError");
            else
                log.debug("All required parameters are included");
            
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
        return ambasadoro.getTpVendorCode();
    }

    public JSONArray getJSONProperties(){
        return tpMeta.getJSONArray("properties");
    }

    public JSONArray getJSONRequiredParameters(){
        return tpMeta.getJSONArray("requiredParameters");
    }

    public boolean hasExtraParameters(){
        JSONArray extraParameters = tpMeta.getJSONArray("extraParameters");
        return (extraParameters != null && extraParameters.length() > 0);
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

    public LTIToolProvider getToolProvider(){
        return this.tp;
    }
    
    public Map<String, String> getParameters(){
        return this.tp.getParameters();
    }

    public String getParameter(String key){
        return this.tp.getParameter(key);
    }

    public void putParameter(String key, String value){
        this.tp.putParameter(key, value);
    }

    // Implementation for SSO
    public String getSSOURL() throws Exception {
        String ssoURL;
        ssoURL = null;
        return ssoURL;
    }
}
