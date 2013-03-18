package com.jpcf.backend.domain

class SlideShowImage {
    String fileName
	String text
    Integer orderIndex
    
    static constraints = {
        fileName nullable: true
    }
    
    static mapping = { sort "orderIndex" }
}
