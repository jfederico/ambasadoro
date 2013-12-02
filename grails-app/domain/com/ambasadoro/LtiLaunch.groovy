package com.ambasadoro

import java.util.Date;

class LtiLaunch {
    // Auto Timestamp
    Date dateCreated
    Date lastUpdated

    LtiResourceLink ltiResourceLink
    static belongsTo = [ltiResourceLink : LtiResourceLink]
    
    LtiUser ltiUser
    
    String launchPresentationLocale = "en"
    String resultSourcedId

    //String tokenID
    //Date tokenCreated = new Date()
    //Boolean tokenAccessed = false

    static hasMany = [ltiAccessLogs:LtiAccessLog]
    static mapping = {
        ltiAccessLogs cascade: 'all'
    }

    static constraints = {
    }

    String toString() {"[${this.ltiResourceLink.resourceLinkId}]${this.ltiUser}:${this.resultSourcedId}"}
}
