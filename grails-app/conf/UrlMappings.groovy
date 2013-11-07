class UrlMappings {

	static mappings = {
        /*
         * URL mapping for LTI access 
         *  Handled by LtiController
         */
        "/lti/tool?"(controller:'tool_launch')

		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}
}
