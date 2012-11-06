package com.jpcf.backend.domain

class Podcast {
    String name = ""
    String description = ""
    String fileName
    Date dateCreated

    static constraints = {
        name nullable: true
        description nullable: true
        fileName nullable: true
    }
}
