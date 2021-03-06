package com.jpcf.backend.domain

import grails.converters.JSON

class JSController {
    def listEvents() {
        def formatter = new java.text.SimpleDateFormat("MM/dd/yyyy hh:mm a")
        def events = Event.findAllByStartDateGreaterThan(new Date()).collect {[name: it.name, description: it.description, startDate: formatter.format(it.startDate), endDate: formatter.format(it.endDate), hasHtml: it.htmlData != null && it.htmlData != "", pageLink: createLink(controller: "event", action: "getHtml", id: it.id, absolute: true)]}
        
        withFormat {
            json {render events as JSON}
            js   {render "${params.callback}(${events as JSON})"}
        }
    }
    
    def listPodcasts(Integer max) {
        def list = Podcast.list().collect {[url: createLink(action: "getPodcastJs", id: it.id), name: it.name, description: it.description]}
        
        println "LIST: ${list}"
        
        withFormat {
            json {render list as JSON}
            js   {render "${params.callback}(${list as JSON})"}
        }
    }

    def getPodcastJs(Long id) {
        def podcast = Podcast.get(id)
        
        if (podcast) {
            def file = new File(podcast.fileName) 
            response.setHeader("Content-Type", "binary/octet-stream") 
            response.setHeader("Content-Disposition", "attachment; filename=${file.getName()}") 
            response.setHeader("Content-Length", "${file.size()}") 

            response.outputStream << file.newInputStream()
        }
        else {
            
        }
    }    
    
    def listImages() {
        withFormat {
            json {render SlideShowImage.list().collect {[url: createLink(action: 'getImageJs', id: it.id, absolute: true), text: it.text]} as JSON}
            js   {render "${params.callback}(${SlideShowImage.list().collect {[url: createLink(action: 'getImageJs', id: it.id, absolute: true), text: it.text]} as JSON})"}
        }
    }
    
    def listImageUrls() {
        def urls = []
        SlideShowImage.list().each {image ->
            urls += createLink(action: "getImageJs", id: image.id)
        }
        
        withFormat {
            json {render urls as JSON}
            js   {render "${params.callback}(${urls as JSON})"}
        }
    }
    
    def getImageJs(Long id) {
        def image = SlideShowImage.get(id)
        
        if (image) {
            def file = new File(image.fileName) 
            response.setHeader("Content-Type", "binary/octet-stream") 
            response.setHeader("Content-Disposition", "attachment; filename=${file.getName()}") 
            response.setHeader("Content-Length", "${file.size()}") 

            response.outputStream << file.newInputStream()
        }
        else {
            
        }
    }
}
