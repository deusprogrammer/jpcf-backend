package com.jpcf.backend.domain

class Event {
    String name
    String description
    Date startDate
    Date endDate
    
    static constraints = {
    }
    
    static mapping = { sort "startDate" }
}
