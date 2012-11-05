package com.jpcf.backend.domain

class SlideShowImage {
    String fileName
    Integer orderIndex
    
    static constraints = {
        fileName nullable: true
    }
    
    static mapping = { sort "orderIndex" }
}
