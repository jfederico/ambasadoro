package com.ambasadoro

import java.util.Date;

class LtiAccessLog {
    // Auto Timestamp
    Date dateCreated

    LtiLaunch ltiLaunch
    static belongsTo = [ltiLaunch : LtiLaunch]

    Date tokenGenerated
    String authToken
    Boolean tokenAccessed = false
    
    static constraints = {
        authToken(nullable:true)
        tokenGenerated(nullable:true)
    }
    
    String toString() {"${this.id}],tokenGeneratedOn=${this.tokenGenerated}:authToken=${this.authToken}:tokenAccessed=${this.tokenAccessed}"}
}
