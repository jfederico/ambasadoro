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

    def getLtiToolConsumer(ambasadoro, params, ltiConstants) {
        def ltiToolConsumer = null
        
        def toolConsumerInstanceGuid =  params.containsKey(ltiConstants.TOOL_CONSUMER_INSTANCE_GUID)? params.get(ltiConstants.TOOL_CONSUMER_INSTANCE_GUID): null
        if( toolConsumerInstanceGuid != null ){
            ltiToolConsumer = LtiToolConsumer.findWhere(ambasadoro: ambasadoro, toolConsumerInstanceGuid: toolConsumerInstanceGuid)
            if( ltiToolConsumer == null ){
                ltiToolConsumer = new LtiToolConsumer()
                ltiToolConsumer.ambasadoro = ambasadoro
                ltiToolConsumer.toolConsumerInstanceGuid = toolConsumerInstanceGuid
            }
            ltiToolConsumer.toolConsumerInstanceName = params.containsKey(ltiConstants.TOOL_CONSUMER_INSTANCE_NAME) ? params.get(ltiConstants.TOOL_CONSUMER_INSTANCE_NAME): ""
            ltiToolConsumer.toolConsumerInstanceDescription = params.containsKey(ltiConstants.TOOL_CONSUMER_INSTANCE_DESCRIPTION) ? params.get(ltiConstants.TOOL_CONSUMER_INSTANCE_DESCRIPTION): ""
            ltiToolConsumer.toolConsumerInstanceUrl = params.containsKey(ltiConstants.TOOL_CONSUMER_INSTANCE_URL) ? params.get(ltiConstants.TOOL_CONSUMER_INSTANCE_URL): ""
            ltiToolConsumer.toolConsumerInfoProductFamilyCode = params.containsKey(ltiConstants.TOOL_CONSUMER_INFO_PRODUCT_FAMILY_CODE) ? params.get(ltiConstants.TOOL_CONSUMER_INFO_PRODUCT_FAMILY_CODE): ""
            ltiToolConsumer.toolConsumerInfoVersion = params.containsKey(ltiConstants.TOOL_CONSUMER_INFO_VERSION) ? params.get(ltiConstants.TOOL_CONSUMER_INFO_VERSION): ""
            ltiToolConsumer.outcomeServiceUrl = params.containsKey(ltiConstants.LIS_OUTCOME_SERVICE_URL) ? params.get(ltiConstants.LIS_OUTCOME_SERVICE_URL): ""
        }
        
        return ltiToolConsumer
    }

}
