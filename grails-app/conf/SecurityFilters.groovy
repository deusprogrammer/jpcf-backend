/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mich4570
 */
class SecurityFilters {
    def filters = {
        everythingElse(controller: "user", action: "promptLogin|login|create|save", invert: true) {
            before = {
                println "IN CATCH-ALL FILTER"
                println "\tCONTROLLER NAME: " + controllerName
                println "\tACTION NAME:     " + actionName
                println "\tLOGGED IN USER:  " + session["userId"]
				
				//Look for specific actions
				switch(actionName) {
					case "getHtml":
						return true
					default:
						//Fall through
						break
				}
                
				//Look for specific controllers
                switch(controllerName) {
                    case "JS":
                        return true
                    default:
                        if(!session["userId"]) {
                            println ""
                            redirect(controller: "user", action: "promptLogin")
                            return true
                        }
                }
                
            }
        }
    }
}

