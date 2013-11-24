import com.ambasadoro.Ambasadoro

class BootStrap {

    def init = { servletContext ->
        log.debug "Bootstrapping Ambasadoro"
        def ambasadoro
        log.debug "Adding/Updating record for Test"
        ambasadoro = Ambasadoro.findById(1)
        if (ambasadoro == null) {
            log.debug "Creating test instance"
            ambasadoro = new Ambasadoro()
        }
        ambasadoro.ltiKey = "test"
        ambasadoro.ltiSecret = "123"
        ambasadoro.tpTitle = "Tester"
        ambasadoro.tpDescription = "This is a LTI Tool Provider created for testing"
        ambasadoro.tpVendorCode = "test"
        ambasadoro.tpEndpoint = "http://localhost/"
        ambasadoro.tpKey = "xxx"
        ambasadoro.tpSecret = "yyy"
        ambasadoro.tpMeta = "{'properties': [{'name': 'allowEmailAuthentication','type': 'boolean', 'value': 'true'}], 'requiredParameters': ['resource_link_title', 'user_id', 'roles', 'lis_person_contact_email_primary', 'tool_consumer_instance_guid'], 'extraParameters': [{'name': 'toc','type': 'text', 'defaultValue': 'select'}]}"
        ambasadoro.tcVendorCode = "moodle"
        ambasadoro.tcMeta = "{'overrides': [{'source': 'resource_link_title', 'target': 'custom_resource_link_title'}, {'source': 'tool_consumer_instance_guid', 'target': 'custom_tool_consumer_instance_guid'}]}"
        log.debug ambasadoro
        if ( !ambasadoro.save(flush:true) ){
            log.debug "Ambasadoro instance for Test cound NOT be saved"
        } else {
            log.debug "Ambasadoro instance for Test saved"
        }
        log.debug "Adding/Updating record for BigBlueButton"
        ambasadoro = Ambasadoro.findById(2)
        if (ambasadoro == null) {
            log.debug "Creating bigbluebutton instance"
            ambasadoro = new Ambasadoro()
        }
        ambasadoro.ltiKey = "bbb"
        ambasadoro.ltiSecret = "welcome"
        ambasadoro.tpTitle = "BigBlueButton"
        ambasadoro.tpDescription = "This is a wrap for adding LTI Tool Provider capabilities to a BigBlueButton server"
        ambasadoro.tpVendorCode = "bigbluebutton"
        ambasadoro.tpEndpoint = "http://test-install.blindsidenetworks.com/bigbluebutton/"
        ambasadoro.tpKey = "xxx"
        ambasadoro.tpSecret = "8cd8ef52e8e101574e400365b55e11a6"
        ambasadoro.tpMeta = "{'properties': [], 'requiredParameters': ['tool_consumer_instance_guid','resource_link_title', 'user_id', 'roles'], 'extraParameters': []}"
        ambasadoro.tcVendorCode = "moodle"
        ambasadoro.tcMeta = "{'overrides': [{'source': 'resource_link_title', 'target': 'custom_resource_link_title'}, {'source': 'tool_consumer_instance_guid', 'target': 'custom_tool_consumer_instance_guid'}]}"
        log.debug ambasadoro
        if ( !ambasadoro.save(flush:true) ){
            log.debug "Ambasadoro instance for BigBlueButton cound NOT be saved"
        } else {
            log.debug "Ambasadoro instance for BigBlueButton saved"
        }

        
        
    }
    def destroy = {
    }
}
