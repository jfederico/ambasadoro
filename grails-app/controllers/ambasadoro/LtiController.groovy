package ambasadoro

class LtiController {

	AmbasadoroService ambasadoroService
	
    def index() { 
		ambasadoroService.logParameters(params)
	}
	
	def tool() {
		ambasadoroService.logParameters(params)
	}
	
	def tool_cartridge() {
		ambasadoroService.logParameters(params)
	}
	
	def tool_ui() {
		ambasadoroService.logParameters(params)
	}
	
}
