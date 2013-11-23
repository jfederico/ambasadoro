package com.ambasadoro

class Ambasadoro {
    // Auto Timestamp
    Date dateCreated
    Date lastUpdated

    String  ltiKey
    String  ltiSecret
    String  tpTitle
    String  tpDescription
    String  tpVendorCode
    String  tpEndpoint
    String  tpKey
    String  tpSecret
    String  tpMeta              = "{'properties': [], 'requiredParameters': [], 'extraParameters': []}"
    String  tcVendorCode
    String  tcMeta              = "{'overrides': []}"

    static hasMany = [ltiToolConsumers:LtiToolConsumer]
    static mapping = {
        ltiToolConsumers cascade: 'all'
        tpMeta sqlType: 'text'
        tcMeta sqlType: 'text'
    }

    static constraints = {
    }
    
    String toString() {"[${this.ltiKey}:${this.ltiSecret}]${this.tpTitle}:${this.tpDescription}:${this.tpVendorCode}:${this.tcVendorCode}:${this.tpMeta}:${this.tcMeta}"}
    
}
