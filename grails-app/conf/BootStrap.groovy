import com.ambasadoro.Ambasadoro

class BootStrap {

    def init = { servletContext ->
        log.debug "Bootstrapping Ambasadoro"
        /// Administrator user and role.
        def ambasadoro = Ambasadoro.findById(1)
        if (ambasadoro == null) {
            log.debug "Creating test instance"
            ambasadoro = new Ambasadoro()
        }
        ambasadoro.ltiKey = "test"
        ambasadoro.ltiSecret = "123"
        ambasadoro.ltiEndPoint = "http://192.168.0.21/ambasadoro/lti/tool/1"
        ambasadoro.toolCode = "test"
        ambasadoro.toolConsumerCode = "moodle"
        ambasadoro.meta = "{\"meta\":{\"properties\":{\"toolKey\":\"xxx\",\"toolSecret\":\"yyy\",\"toolEndPoint\":\"http://localhost/\"}}}"
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
