package ambasadoro

class AmbasadoroService {

    def logParameters(Object params) {
        log.debug "----------------------------------"
        for( param in params ) log.debug "${param.getKey()}=${param.getValue()}"
        log.debug "----------------------------------"

    }
}
