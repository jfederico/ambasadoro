package ambasadoro

class Entity {
    // Auto Timestamp
    Date dateCreated
    Date lastUpdated

    String  lti_key
    String  lti_secret
    String  tc_type
    String  tp_type

    static constraints = {
    }
}
