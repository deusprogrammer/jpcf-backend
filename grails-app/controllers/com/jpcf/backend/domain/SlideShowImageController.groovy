package com.jpcf.backend.domain

import grails.converters.JSON

class SlideShowImageController {
    
    def SlideShowImageService
    
    def index(Long id) {
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
    
    def delete(Long id) {
        def image = SlideShowImage.get(id)
        
        if (!image) {
            flash.message = "Unable to find image"
            redirect(controller: "configuration", action: "index")
            return
        }
        
        /*
        println "DELETING ${image.fileName}"
        def file = new File(image.fileName)
        if (!file.delete()) {
            flash.message = "Unable to delete file from hard drive"
            redirect(controller: "configuration", action: "index")  
            return
        }
        */
        
        if (!image.delete()) {
            flash.message = "Unable to delete file from database"
            redirect(controller: "configuration", action: "index")
            return
        }
        
        redirect(controller: "configuration", action: "index")
    }
    
    def moveUp(Long id) {
        def ret = [status: "success"]
        SlideShowImageService.moveUp(id)
        render ret as JSON
    }
    
    def moveDown(Long id) {
        def ret = [status: "success"]
        SlideShowImageService.moveDown(id)
        render ret as JSON
    }
}