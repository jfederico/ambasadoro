package com.ambasadoro

class Ambasadoro {
    // Auto Timestamp
    Date dateCreated
    Date lastUpdated

    String  ltiKey
    String  ltiSecret
    String  ltiEndPoint
    String  toolCode
    String  toolConsumerCode
    String  meta

    static constraints = {
    }
    
    String toString() {"[${this.ltiKey}:${this.ltiSecret}]${this.toolCode}:${this.toolConsumerCode}::${this.meta}"}
    
}
