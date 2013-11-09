package ambasadoro

import net.oauth.OAuth;

class AmbasadoroService {

    def logParameters(Object params) {
        log.debug "----------------------------------"
        for( param in params ) log.debug "${param.getKey()}=${param.getValue()}"
        log.debug "----------------------------------"
    }
    
    def getAmbasadoroInstance(params) throws Exception{
        Ambasadoro a
        if (params.containsKey(OAuth.OAUTH_CONSUMER_KEY)) {         //oauth_consumer_key
            def ltiKey = params.get(OAuth.OAUTH_CONSUMER_KEY)
            a = Ambasadoro.findByLtiKey(ltiKey)
            if( a == null)
                throw new Exception("There is no instance for ltiKey " + "[" + ltiKey + "].")
        } else {
            throw new Exception("Parameter " + "[" + OAuth.OAUTH_CONSUMER_KEY + "] not included.")
        }
        return a

    }
}
