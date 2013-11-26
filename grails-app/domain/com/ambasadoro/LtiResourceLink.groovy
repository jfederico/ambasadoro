package com.ambasadoro

import java.util.Date
import org.codehaus.groovy.grails.web.json.JSONArray
import org.codehaus.groovy.grails.web.json.JSONObject

class LtiResourceLink {
    // Auto Timestamp
    Date dateCreated
    Date lastUpdated

    LtiContext ltiContext
    static belongsTo = [ltiContext:LtiContext]

    String resourceLinkId
    String resourceLinkTitle
    String resourceLinkDescription
    String resourceLinkExtra

    static hasMany = [ltiLaunches:LtiLaunch]
    static mapping = {
        ltiLaunches cascade: 'all'
        resourceLinkDescription sqlType: 'text'
        resourceLinkExtra sqlType: 'text'
    }

    static constraints = {
    }

    String toString() {"[${this.ltiContext.id}:${this.resourceLinkId}]${this.resourceLinkTitle}:${this.resourceLinkExtra}"}

    String getExtraParameterValue(name){
        def extraParameterValue = null
        try{
            JSONObject extraParameters = new JSONObject(this.resourceLinkExtra)
            extraParameterValue = extraParameters.getString(name)
        } catch( Exception e){
        }
        return extraParameterValue
        
        //for(int i=0; i < extraParameters.length(); i++){
        //    if( extraParameters.getJSONArray('name') == name ){
        //        return extraParameters.getJSONArray('name')
        //   }
        //}
    }
}
