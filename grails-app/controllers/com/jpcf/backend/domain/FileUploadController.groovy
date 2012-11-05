package com.jpcf.backend.domain

import grails.converters.JSON

class FileUploadController {
    static transactional = true
    def allowedMethods = []
    
    def SlideShowImageService
    
    def uploadPodcast = {
        println "IN upload()"
        
        def f = request.getFile('podcastUpload')
        if(!f.empty) {
            //def image = new SlideShowImage(orderIndex: SlideShowImageService.getNextOrderIndex())
            def podcast = new Podcast()
            
            def filename = f.getOriginalFilename()
            def extension = filename[filename.lastIndexOf(".")..filename.size() - 1]
            
            podcast.save()
            
            def path = grailsApplication.config.podcasts.location.toString() + "/podcast" + podcast.id + extension

            podcast.fileName = path
            podcast.save()
            
            println "FILE: " + path
            
            new File(grailsApplication.config.podcasts.location.toString()).mkdirs()
            f.transferTo(new File(path))
        }    
        else {
            flash.message = 'File cannot be empty!'
        }
        
        redirect(controller: "configuration", action: "index")
    }

    def uploadImage = {
        println "IN upload()"
        
        def f = request.getFile('imageUpload')
        if(!f.empty) {
            def image = new SlideShowImage(orderIndex: SlideShowImageService.getNextOrderIndex())
            
            def filename = f.getOriginalFilename()
            def extension = filename[filename.lastIndexOf(".")..filename.size() - 1]
            
            image.save()
            
            def path = grailsApplication.config.images.location.toString() + "/image" + image.id + extension

            image.fileName = path
            image.save()
            
            println "FILE: " + path
            
            new File(grailsApplication.config.images.location.toString()).mkdirs()
            f.transferTo(new File(path))
        }    
        else {
            flash.message = 'File cannot be empty!'
        }
        
        redirect(controller: "configuration", action: "index")
    }
}