package com.ambasadoro

import com.ambasadoro.exceptions.AmbasadoroException

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
                throw new AmbasadoroException("There is no instance for id " + "[" + id + "].", "AmbasadoroError")
        } else {
            throw new AmbasadoroException("Parameter [id] not included.", "AmbasadoroError")
        }
        return ambasadoro

    }

    def retrieveEndpoint() {
        return retrieveEndpoint("http")
    }

    def retrieveEndpoint(protocol) {
        return protocol + "://" + endpoint + "/ambasadoro"
    }
    
    def saveLaunch(ltiConstants, ambasadoro, params) throws AmbasadoroException, Exception {
        
    }
    
    def saveUser(ltiConstants, ambasadoro, params) throws AmbasadoroException, Exception {
        def ltiUser = null
        def ltiToolConsumer = getLtiToolConsumer(ltiConstants, ambasadoro, params)
        if( ltiToolConsumer == null ) {
            log.debug " - The ltiToolConsumer couldn't be generated"
            throw new AmbasadoroException("The ltiToolConsumer couldn't be generated", "AmbasadoroError")
        } else if ( !ltiToolConsumer.save(flush:true) ){
            log.debug " - The ltiToolConsumer couldn't be saved"
            throw new AmbasadoroException("The ltiToolConsumer couldn't be saved", "AmbasadoroError")
        } else {
            log.debug " - The ltiToolConsumer was saved"
            ltiUser = getLtiUser(ltiConstants, ambasadoro, params, ltiToolConsumer)
            if(ltiUser == null){
                log.debug " - The ltiUser couldn't be generated"
                throw new AmbasadoroException("The ltiUser couldn't be generated", "AmbasadoroError")
            } else if( !ltiUser.save(flush:true) ){
                log.debug " - The ltiUser couldn't be saved"
                throw new AmbasadoroException("The ltiUser couldn't be generated", "AmbasadoroError")
            } else {
                log.debug " - The ltiUser was saved"
            }
        }
        return ltiUser
    }

    def getLtiUser(ltiConstants, ambasadoro, params, ltiToolConsumer) {
        def ltiUser = null

        String userId = params.containsKey(ltiConstants.USER_ID) ? params.get(ltiConstants.USER_ID): null
        if( userId != null ){
            ltiUser = LtiUser.findWhere(ltiToolConsumer: ltiToolConsumer, userId: userId)
            if( ltiUser == null ){
                ltiUser = new LtiUser()
                ltiUser.ltiToolConsumer = ltiToolConsumer
                ltiUser.userId = userId
            }
            ltiUser.lisPersonContactEmailPrimary = params.containsKey(ltiConstants.LIS_PERSON_CONTACT_EMAIL_PRIMARY) ? params.get(ltiConstants.LIS_PERSON_CONTACT_EMAIL_PRIMARY): ""
            ltiUser.lisPersonNameGiven = params.containsKey(ltiConstants.LIS_PERSON_NAME_GIVEN)? params.get(ltiConstants.LIS_PERSON_NAME_GIVEN): ""
            ltiUser.lisPersonNameFamily = params.containsKey(ltiConstants.LIS_PERSON_NAME_FAMILY)? params.get(ltiConstants.LIS_PERSON_NAME_FAMILY): ""
            ltiUser.lisPersonNameFull = params.containsKey(ltiConstants.LIS_PERSON_NAME_FULL)? params.get(ltiConstants.LIS_PERSON_NAME_FULL): ""
        }

        return ltiUser
    }
    
    def getLtiToolConsumer(ltiConstants, ambasadoro, params) {
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
    
    def sanitizePrameters(params){
        // Remove extra parameters added by grails
        params.remove("id")
        params.remove("action")
        params.remove("controller")
    }
}
