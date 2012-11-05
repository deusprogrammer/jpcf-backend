package com.jpcf.backend.domain

import org.springframework.dao.DataIntegrityViolationException

class PodcastController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [podcastInstanceList: Podcast.list(params), podcastInstanceTotal: Podcast.count()]
    }

    def create() {
        [podcastInstance: new Podcast(params)]
    }

    def save() {
        def podcastInstance = new Podcast(params)
        if (!podcastInstance.save(flush: true)) {
            render(view: "create", model: [podcastInstance: podcastInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'podcast.label', default: 'Podcast'), podcastInstance.id])
        redirect(action: "show", id: podcastInstance.id)
    }

    def show(Long id) {
        def podcastInstance = Podcast.get(id)
        if (!podcastInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'podcast.label', default: 'Podcast'), id])
            redirect(action: "list")
            return
        }

        [podcastInstance: podcastInstance]
    }

    def edit(Long id) {
        def podcastInstance = Podcast.get(id)
        if (!podcastInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'podcast.label', default: 'Podcast'), id])
            redirect(action: "list")
            return
        }

        [podcastInstance: podcastInstance]
    }

    def update(Long id, Long version) {
        def podcastInstance = Podcast.get(id)
        if (!podcastInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'podcast.label', default: 'Podcast'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (podcastInstance.version > version) {
                podcastInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'podcast.label', default: 'Podcast')] as Object[],
                          "Another user has updated this Podcast while you were editing")
                render(view: "edit", model: [podcastInstance: podcastInstance])
                return
            }
        }

        podcastInstance.properties = params

        if (!podcastInstance.save(flush: true)) {
            render(view: "edit", model: [podcastInstance: podcastInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'podcast.label', default: 'Podcast'), podcastInstance.id])
        redirect(action: "show", id: podcastInstance.id)
    }

    def delete(Long id) {
        def podcastInstance = Podcast.get(id)
        if (!podcastInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'podcast.label', default: 'Podcast'), id])
            redirect(action: "list")
            return
        }

        try {
            podcastInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'podcast.label', default: 'Podcast'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'podcast.label', default: 'Podcast'), id])
            redirect(action: "show", id: id)
        }
    }
    
    def get(Long id) {
        def podcast = Podcast.get(id)
        
        if (image) {
            def file = new File(podcast.fileName) 
            response.setHeader("Content-Type", "binary/octet-stream") 
            response.setHeader("Content-Disposition", "attachment; filename=${file.getName()}") 
            response.setHeader("Content-Length", "${file.size()}") 

            response.outputStream << file.newInputStream()
        }
        else {
            
        }
    }
}
