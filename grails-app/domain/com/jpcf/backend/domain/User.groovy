package com.jpcf.backend.domain

class User {
    String username
    String password
    String salt
    
    String toString() {
        return username
    }
    
    def beforeInsert = {
        salt = new Date().getTime()
        def passAndSalt = salt + "--" + password
        //println "PASS+SALT: ${passAndSalt}"
        password = passAndSalt.encodeAsMD5()
    }

    static constraints = {
        username unique: true
        password nullable:false
        salt nullable:true
    }
}
