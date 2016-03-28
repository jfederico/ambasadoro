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
    Integer resourceLinkExtraReset

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
    }

    String getSpecialParameterValue(name){
        def specialParameterValue = null
        try{
            JSONObject specialParameters = new JSONObject(this.resourceLinkSpecial)
            specialParameterValue = specialParameters.getString(name)
        } catch( Exception e){
        }
        return specialParameterValue
    }
}
