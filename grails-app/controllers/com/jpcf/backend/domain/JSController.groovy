package com.jpcf.backend.domain

import grails.converters.JSON

class JSController {
    def listEvents() {
        withFormat {
            json {render Event.list() as JSON}
            js   {render "${params.callback}(${Event.list() as JSON})"}
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
            json {render SlideShowImage.list() as JSON}
            js   {render "${params.callback}(${SlideShowImage.list() as JSON})"}
        }
    }
    
    def listImageUrls() {
        def urls = []
        SlideShowImage.list().each {image ->
            urls += createLink(action: "getImageJs", id: image.id)
        }
        
        println "URLS: ${urls}"
        
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