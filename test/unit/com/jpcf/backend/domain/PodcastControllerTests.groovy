package com.jpcf.backend.domain



import org.junit.*
import grails.test.mixin.*

@TestFor(PodcastController)
@Mock(Podcast)
class PodcastControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/podcast/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.podcastInstanceList.size() == 0
        assert model.podcastInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.podcastInstance != null
    }

    void testSave() {
        controller.save()

        assert model.podcastInstance != null
        assert view == '/podcast/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/podcast/show/1'
        assert controller.flash.message != null
        assert Podcast.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/podcast/list'

        populateValidParams(params)
        def podcast = new Podcast(params)

        assert podcast.save() != null

        params.id = podcast.id

        def model = controller.show()

        assert model.podcastInstance == podcast
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/podcast/list'

        populateValidParams(params)
        def podcast = new Podcast(params)

        assert podcast.save() != null

        params.id = podcast.id

        def model = controller.edit()

        assert model.podcastInstance == podcast
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/podcast/list'

        response.reset()

        populateValidParams(params)
        def podcast = new Podcast(params)

        assert podcast.save() != null

        // test invalid parameters in update
        params.id = podcast.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/podcast/edit"
        assert model.podcastInstance != null

        podcast.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/podcast/show/$podcast.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        podcast.clearErrors()

        populateValidParams(params)
        params.id = podcast.id
        params.version = -1
        controller.update()

        assert view == "/podcast/edit"
        assert model.podcastInstance != null
        assert model.podcastInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/podcast/list'

        response.reset()

        populateValidParams(params)
        def podcast = new Podcast(params)

        assert podcast.save() != null
        assert Podcast.count() == 1

        params.id = podcast.id

        controller.delete()

        assert Podcast.count() == 0
        assert Podcast.get(podcast.id) == null
        assert response.redirectedUrl == '/podcast/list'
    }
}
