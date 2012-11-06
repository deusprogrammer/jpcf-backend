package com.jpcf.backend.domain

import grails.converters.JSON

class JSController {
    def listEventsJs() {
        render Event.list() as JSON
    }
    
    def listPodcastsJs(Integer max) {
        def list = Podcast.list().collect {[url: createLink(action: "getPodcastJs", id: it.id), name: it.name, description: it.description]}
        
        render list() as JSON
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
    
    def listImagesJS() {
        render SlideShowImage.list() as JSON
    }
    
    def listImageUrlsJS() {
        def urls = []
        SlideShowImage.list().each {image ->
            urls += createLink(action: "getImageJs", id: image.id)
        }
        
        render urls as JSON
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
