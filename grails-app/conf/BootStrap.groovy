import com.ambasadoro.Ambasadoro

class BootStrap {

    def init = { servletContext ->
        log.debug "Bootstrapping Ambasadoro"
        def ambasadoro = Ambasadoro.findById(1)
        if (ambasadoro == null) {
            log.debug "Creating test instance"
            ambasadoro = new Ambasadoro()
        }
        ambasadoro.ltiKey = "test"
        ambasadoro.ltiSecret = "123"
        ambasadoro.tpTitle = "Tester"
        ambasadoro.tpDescription = "This is a testing Tool Provider"
        ambasadoro.tpVendorCode = "test"
        ambasadoro.tpEndpoint = "http://localhost/"
        ambasadoro.tpKey = "xxx"
        ambasadoro.tpSecret = "yyy"
        ambasadoro.tpExtraProperties = "{'allowEmailAuthentication': {'type': 'boolean', 'value': 'true'} }"
        ambasadoro.tpRequiredParameters = "['resource_link_title', 'user_id', 'roles', 'lis_person_contact_email_primary', 'tool_consumer_instance_guid']"
        ambasadoro.tcVendorCode = "moodle"
        ambasadoro.tcOverride = "{}"
        log.debug ambasadoro
        if ( !ambasadoro.save(flush:true) ){
            log.debug "Ambasadoro instance cound NOT be saved"
        } else {
            log.debug "Ambasadoro instance saved"
        }
    }
    def destroy = {
    }
}
