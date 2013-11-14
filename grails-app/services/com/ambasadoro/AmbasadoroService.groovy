package com.ambasadoro

import net.oauth.OAuth;

class AmbasadoroService {
    def endpoint

    def logParameters(Object params) {
        log.debug "----------------------------------"
        for( param in params ) log.debug "${param.getKey()}=${param.getValue()}"
        log.debug "----------------------------------"
    }

    def getAmbasadoroInstance(params) throws Exception{
        Ambasadoro ambasadoro
        if (params.containsKey("id")) {
            def id = params.get("id")
            ambasadoro = Ambasadoro.findById(id)
            if( ambasadoro == null)
                throw new Exception("There is no instance for id " + "[" + id + "].")
        } else {
            throw new Exception("Parameter " + "[" + OAuth.OAUTH_CONSUMER_KEY + "] not included.")
        }
        return ambasadoro

    }

    def retrieveEndpoint() {
        return retrieveEndpoint("http")
    }

    def retrieveEndpoint(protocol) {
        return protocol + "://" + endpoint + "/ambasadoro"
    }
}
