package com.jpcf.backend.domain

import org.springframework.dao.DataIntegrityViolationException
import grails.converters.JSON
import java.text.ParseException
import java.text.SimpleDateFormat

class EventController {

    static allowedMethods = [save: "POST", update: "POST"]

    def index() {
        redirect(action: "list")
    }

    def create() {
        [eventInstance: new Event(params)]
    }

    def save() {
		SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy h:m a")
		def startDate
		def endDate
		
		try {
			startDate = formatter.parse(params.startDate)
			endDate = formatter.parse(params.endDate)
		} catch (ParseException e) {
			flash.message = "Start and end date must be in format MM/dd/yyyy H:m(pm|am)!"
			redirect(controller: "configuration", action: "index")
			return
		}
		
		if (!startDate || !endDate) {
			flash.message = "Start and end date must be in format MM/dd/yyyy H:m(pm|am)!"
			redirect(controller: "configuration", action: "index")
			return
		}
		
		params.startDate = startDate
		params.endDate = endDate
		
        def eventInstance = new Event(params)
        if (!eventInstance.save(flush: true)) {
            flash.message = "Unable to save edit!"
            redirect(controller: "configuration", action: "index")    
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])
        redirect(controller: "configuration", action: "index")
    }

    def show(Long id) {
        def eventInstance = Event.get(id)
        if (!eventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), id])
            redirect(action: "list")
            return
        }

        [eventInstance: eventInstance]
    }

    def edit(Long id) {
        def eventInstance = Event.get(id)
        if (!eventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), id])
            redirect(action: "list")
            return
        }

        [eventInstance: eventInstance]
    }

    def update(Long id, Long version) {
        def eventInstance = Event.get(id)
        if (!eventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), id])
            redirect(controller: "configuration", action: "index")
            return
        }

        if (version != null) {
            if (eventInstance.version > version) {
                eventInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'event.label', default: 'Event')] as Object[],
                          "Another user has updated this Event while you were editing")
                render(view: "edit", model: [eventInstance: eventInstance])
                return
            }
        }

        eventInstance.properties = params

        if (!eventInstance.save(flush: true)) {
            render(view: "edit", model: [eventInstance: eventInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'event.label', default: 'Event'), eventInstance.id])
        redirect(controller: "configuration", action: "index")
    }

    def delete(Long id) {
        def eventInstance = Event.get(id)
        if (!eventInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'event.label', default: 'Event'), id])
            redirect(controller: "configuration", action: "index")
            return
        }

        try {
            eventInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'event.label', default: 'Event'), id])
            redirect(controller: "configuration", action: "index")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'event.label', default: 'Event'), id])
            redirect(controller: "configuration", action: "index")
        }
    }
}