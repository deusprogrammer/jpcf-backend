package com.jpcf.backend.domain

import org.springframework.web.context.request.RequestContextHolder

class SessionService {
    def checkCredentials(def username, def password) {
        def user = User.findByUsername(username)
        
        if (user) {
            def passAndSalt = user.salt + "--" + password
            def hashedPass = passAndSalt.encodeAsMD5()
            
            //println hashedPass + "/" + user.password
            
            if (hashedPass.equals(user.password)) {
                return true
            }
            else {
                return false
            }
        }
        else {
            println "Unable to find user by that name!"
            return false
        }
    }
}
