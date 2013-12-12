class UrlMappings {

	static mappings = {
        /*
         * URL mapping for LTI access 
         *  Handled by LtiController
         */
        "/$controller/tool?/$id?"{
            action = [GET:"tool_ui", POST:"tool"]
        }

        "/$controller/tool.xml?/$id?"{
            action = 'tool_cartridge'
        }

		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
        "404"(view:'/error')
		"500"(view:'/error')
	}
}
