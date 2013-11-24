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
    
    def saveLtiLaunch(ltiConstants, ambasadoro, params) throws AmbasadoroException, Exception {
        def ltiLaunch = null
        def ltiUser = null
        def ltiContext = null
        def ltiResourceLink = null
        
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

            ltiContext = getLtiContext(ltiConstants, ambasadoro, params, ltiToolConsumer)
            if(ltiContext == null){
                log.debug " - The ltiContext couldn't be generated"
                throw new AmbasadoroException("The ltiContext couldn't be generated", "AmbasadoroError")
            } else if( !ltiContext.save(flush:true) ){
                log.debug " - The ltiContext couldn't be saved"
                throw new AmbasadoroException("The ltiContext couldn't be generated", "AmbasadoroError")
            } else {
                log.debug " - The ltiContext was saved"

                ltiResourceLink = getLtiResourceLink(ltiConstants, ambasadoro, params, ltiContext)
                if(ltiResourceLink == null){
                    log.debug " - The ltiResourceLink couldn't be generated"
                    throw new AmbasadoroException("The ltiResourceLink couldn't be generated", "AmbasadoroError")
                } else if( !ltiResourceLink.save(flush:true) ){
                    log.debug " - The ltiResourceLink couldn't be saved"
                    throw new AmbasadoroException("The ltiResourceLink couldn't be generated", "AmbasadoroError")
                } else {
                    log.debug " - The ltiResourceLink was saved"
                }
            }
        }
        log.debug " - saveLtiLaunch has ended"
        return ltiResourceLink
    }
    
    def saveLtiUser(ltiConstants, ambasadoro, params) throws AmbasadoroException, Exception {
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

    def getLtiContext(ltiConstants, ambasadoro, params, ltiToolConsumer) {
        def context = null

        String contextId = params.containsKey(ltiConstants.CONTEXT_ID) ? params.get(ltiConstants.CONTEXT_ID): null
        if( contextId != null ){
            context = LtiContext.findWhere(ltiToolConsumer: ltiToolConsumer, contextId: contextId)
            if( context == null ){
                context = new LtiContext()
                context.ltiToolConsumer = ltiToolConsumer
                context.contextId = contextId
            }
            context.contextType = params.containsKey(ltiConstants.CONTEXT_TYPE) ? params.get(ltiConstants.CONTEXT_TYPE): ""
            context.contextLabel = params.containsKey(ltiConstants.CONTEXT_LABEL)? params.get(ltiConstants.CONTEXT_LABEL): ""
            context.contextTitle = params.containsKey(ltiConstants.CONTEXT_TITLE)? params.get(ltiConstants.CONTEXT_TITLE): ""
        }

        return context
    }

    def getLtiResourceLink(ltiConstants, ambasadoro, params, ltiContext) {
        def resourceLink = null

        String resourceLinkId = params.containsKey(ltiConstants.RESOURCE_LINK_ID) ? params.get(ltiConstants.RESOURCE_LINK_ID): null
        if( resourceLinkId != null ){
            resourceLink = LtiResourceLink.findWhere(ltiContext: ltiContext, resourceLinkId: resourceLinkId)
            if( resourceLink == null ){
                resourceLink = new LtiResourceLink()
                resourceLink.ltiContext = ltiContext
                resourceLink.resourceLinkId = resourceLinkId
            }
            resourceLink.resourceLinkTitle = params.containsKey(ltiConstants.RESOURCE_LINK_TITLE) ? params.get(ltiConstants.RESOURCE_LINK_TITLE): ""
            resourceLink.resourceLinkDescription = params.containsKey(ltiConstants.RESOURCE_LINK_DESCRIPTION)? params.get(ltiConstants.RESOURCE_LINK_DESCRIPTION): ""
            resourceLink.resourceLinkExtra = ""
        }

        return resourceLink
    }

    def sanitizePrameters(params){
        // Remove extra parameters added by grails
        params.remove("id")
        params.remove("action")
        params.remove("controller")
    }
}
