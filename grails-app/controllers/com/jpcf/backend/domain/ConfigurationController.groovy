package com.jpcf.backend.domain

class ConfigurationController {

    def index() {
        [events: Event.list(), nEvents: Event.count(), images: SlideShowImage.list(), nImages: SlideShowImage.count(), podcasts: Podcast.list(), nPodcasts: Podcast.count()]
    }
}
