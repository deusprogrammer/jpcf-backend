package com.jpcf.backend.domain

class Event {
    String name
    String description
    Date startDate
    Date endDate
	
	String htmlData
    
    static constraints = {
		htmlData nullable: true, maxSize: 10000
    }
    
    static mapping = { sort "startDate" }
}
