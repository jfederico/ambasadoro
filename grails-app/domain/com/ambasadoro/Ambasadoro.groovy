package com.ambasadoro

class Ambasadoro {
    // Auto Timestamp
    Date dateCreated
    Date lastUpdated

    public String  ltiKey
    public String  ltiSecret
    public String  toolCode
    public String  toolConsumerCode
    public String  meta

    static constraints = {
    }
    
    String toString() {"[${this.ltiKey}:${this.ltiSecret}]${this.toolCode}:${this.toolConsumerCode}::${this.meta}"}
    
}
