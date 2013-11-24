package com.ambasadoro

import java.util.Date;

class LtiUser {
    // Auto Timestamp
    Date dateCreated
    Date lastUpdated

    LtiToolConsumer ltiToolConsumer
    static belongsTo = [ltiToolConsumer:LtiToolConsumer]

    String userId = ""
    String lisPersonContactEmailPrimary = ""
    String lisPersonNameGiven = ""
    String lisPersonNameFamily = ""
    String lisPersonNameFull = ""
    String matchingKey = ""
    String matchingValue = ""
    Boolean matchingStatus = false

    static constraints = {
    }

    String toString() {"[${this.ltiToolConsumer}]${this.userId}:${this.lisPersonContactEmailPrimary}"}
}
