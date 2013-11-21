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
    String  tpMeta              = "{'properties': {}, 'requiredParameters': {}}"
    String  tcVendorCode
    String  tcMeta              = "{'override': {}}"

    static constraints = {
    }
    
    String toString() {"[${this.ltiKey}:${this.ltiSecret}]${this.tpTitle}:${this.tpDescription}:${this.tpVendorCode}:${this.tcVendorCode}:${this.tpMeta}:${this.tcMeta}"}
    
}
