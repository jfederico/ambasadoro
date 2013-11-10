import com.ambasadoro.Ambasadoro;

class BootStrap {

    def init = { servletContext ->
       log.debug "Bootstrapping Ambasadoro"
       /// Administrator user and role.
	   def ambasadoro = Ambasadoro.findByLtiKey("test")
      if (ambasadoro == null) {
           log.debug "Creating test instance"
           ambasadoro = new Ambasadoro()
           ambasadoro.ltiKey = "test"
           ambasadoro.ltiSecret = "welcome"
           ambasadoro.toolCode = "test"
           ambasadoro.toolConsumerCode = "moodle"
           ambasadoro.meta = ""
           log.debug ambasadoro
           if ( !ambasadoro.save(flush:true) ){
               log.debug "Ambasadoro instance cound NOT be created"
           } else {
               log.debug "Ambasadoro instance created"
		   }
	   }

    }
    def destroy = {
    }
}
