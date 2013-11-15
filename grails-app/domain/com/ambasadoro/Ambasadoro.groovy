package com.ambasadoro

class Ambasadoro {
    // Auto Timestamp
    Date dateCreated
    Date lastUpdated

    String  ltiKey
    String  ltiSecret
    String  toolTitle
    String  toolDescription
    String  toolVendorCode
    String  toolConsumerVendorCode
    String  meta

    static constraints = {
    }
    
    String toString() {"[${this.ltiKey}:${this.ltiSecret}]${this.toolTitle}:${this.toolDescription}:${this.toolVendorCode}:${this.toolConsumerVendorCode}::${this.meta}"}
    
}
