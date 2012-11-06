package com.jpcf.backend.domain

import org.springframework.dao.DataIntegrityViolationException

class UserController {
    def sessionService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create() {
        [userInstance: new User(params)]
    }

    def save() {
        def userInstance = new User(params)
        if (!userInstance.save(flush: true)) {
            render(view: "create", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def show(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def edit(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        [userInstance: userInstance]
    }

    def update(Long id, Long version) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userInstance.version > version) {
                userInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'user.label', default: 'User')] as Object[],
                          "Another user has updated this User while you were editing")
                render(view: "edit", model: [userInstance: userInstance])
                return
            }
        }

        userInstance.properties = params

        if (!userInstance.save(flush: true)) {
            render(view: "edit", model: [userInstance: userInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
        redirect(action: "show", id: userInstance.id)
    }

    def delete(Long id) {
        def userInstance = User.get(id)
        if (!userInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
            return
        }

        try {
            userInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
            redirect(action: "show", id: id)
        }
    }
    
    def promptLogin() {
        
    }
    
    def login() {
        if (params.username && params.password) {
            if (sessionService.checkCredentials(params.username, params.password)) {
                def user = User.findByUsername(params.username)
                session["userId"] = user.id
                flash.message = "User logged in!"
                redirect(controller: "configuration", action: "index")
                return
            }
            else {
                flash.message = "Invalid login/password"
                redirect(action: "promptLogin")
                return
            }
        }
        else {
            flash.message = "You must enter both username and password"
            redirect(action: "promptLogin")
            return
        }
    }

    def logout() {
        session["userId"] = null
        flash.message = "Successfully logged out."
        redirect(action: "promptLogin")
        return
    }
    
    def promptPasswordChange() {
        if (!session["userId"]) {
            redirect(action: "promptLogin")
            return
        }
        
        def user = User.get(session["userId"])
        
        if (!user) {
            redirect(controller: "error", action: "notFound")
            return
        }
        
        [user: user.id, username: user.username]
    }
    
    def changePassword() {
        if (!session["userId"]) {
            redirect(action: "promptLogin")
        }
        
        def user = User.get(session["userId"])
        
        if (!user) {
            redirect(controller: "error", action: "notFound")
            return
        }
        
        def salt = new Date().getTime()
        def passAndSalt = salt + "--" + params.password
        
        user.salt = salt
        user.password = passAndSalt.encodeAsMD5()
        user.save()
        
        redirect(action: "show", id: session["userId"])
        return
    }
}
