package com.ambasadoro

import java.util.Date;

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
    }

    static constraints = {
    }

    String toString() {"[${this.ltiContext}:${this.resourceLinkId}]${this.resourceLinkExtra}:${this.resourceLinkTitle}"}
}
