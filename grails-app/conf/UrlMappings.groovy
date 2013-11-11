class UrlMappings {

	static mappings = {
        /*
         * URL mapping for LTI access 
         *  Handled by LtiController
         */
        "/$controller/tool?/$id?"{
            action = [GET:"toolUi", POST:"tool"]
        }

        "/$controller/*.xml"{
            action = 'toolCartridge'
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
