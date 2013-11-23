package com.ambasadoro

import java.util.Date;

class LtiAccessLog {
    // Auto Timestamp
    Date dateCreated

    LtiLaunch ltiLaunch
    static belongsTo = [ltiLaunch : LtiLaunch]

    Date authTokenGenerated
    String authToken
    Boolean authTokenAccessed = false
    
    static constraints = {
        authToken(nullable:true)
        authTokenGenerated(nullable:true)
    }
    
    String toString() {"${this.id}],authTokenGenerated=${this.authTokenGenerated}:authToken=${this.authToken}:authTokenAccessed=${this.authTokenAccessed}"}
}
