package com.ambasadoro

import java.util.Date;

class LtiContext {
    // Auto Timestamp
    Date dateCreated
    Date lastUpdated

    LtiToolConsumer ltiToolConsumer
    static belongsTo = [ltiToolConsumer:LtiToolConsumer]

    String contextId
    String contextType
    String contextLabel
    String contextTitle

    static hasMany = [ltiResourceLinks:LtiResourceLink]

    static constraints = {
    }
    
    String toString() {"[${this.ltiToolConsumer}:${this.contextId}]${this.contextType}:${this.contextLabel}:${this.contextTitle}"}
}
