package com.ambasadoro

import java.util.Date;

class LtiToolConsumer {
    // Auto Timestamp
    Date dateCreated
    Date lastUpdated

    Ambasadoro ambasadoro
    static belongsTo = [ambasadoro:Ambasadoro]

    String toolConsumerInstanceGuid
    String toolConsumerInstanceName
    String toolConsumerInstanceDescription
    String toolConsumerInstanceUrl
    String toolConsumerInfoProductFamilyCode
    String toolConsumerInfoVersion

    String outcomeServiceUrl

    static hasMany = [ltiContexts:LtiContext, ltiUsers:LtiUser]
    static mapping = {
        ltiContexts cascade: 'all'
        ltiUsers cascade: 'all'
    }

    static constraints = {
    }
    
    String toString() {"[${this.ambasadoro}:${this.toolConsumerInstanceGuid}]${this.toolConsumerInfoProductFamilyCode}:${this.outcomeServiceUrl}"}
}
