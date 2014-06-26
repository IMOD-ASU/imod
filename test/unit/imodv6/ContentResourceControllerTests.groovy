package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(ContentResourceController)
@Mock(ContentResource)
class ContentResourceControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/contentResource/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.contentResourceInstanceList.size() == 0
        assert model.contentResourceInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.contentResourceInstance != null
    }

    void testSave() {
        controller.save()

        assert model.contentResourceInstance != null
        assert view == '/contentResource/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/contentResource/show/1'
        assert controller.flash.message != null
        assert ContentResource.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/contentResource/list'

        populateValidParams(params)
        def contentResource = new ContentResource(params)

        assert contentResource.save() != null

        params.id = contentResource.id

        def model = controller.show()

        assert model.contentResourceInstance == contentResource
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/contentResource/list'

        populateValidParams(params)
        def contentResource = new ContentResource(params)

        assert contentResource.save() != null

        params.id = contentResource.id

        def model = controller.edit()

        assert model.contentResourceInstance == contentResource
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/contentResource/list'

        response.reset()

        populateValidParams(params)
        def contentResource = new ContentResource(params)

        assert contentResource.save() != null

        // test invalid parameters in update
        params.id = contentResource.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/contentResource/edit"
        assert model.contentResourceInstance != null

        contentResource.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/contentResource/show/$contentResource.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        contentResource.clearErrors()

        populateValidParams(params)
        params.id = contentResource.id
        params.version = -1
        controller.update()

        assert view == "/contentResource/edit"
        assert model.contentResourceInstance != null
        assert model.contentResourceInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/contentResource/list'

        response.reset()

        populateValidParams(params)
        def contentResource = new ContentResource(params)

        assert contentResource.save() != null
        assert ContentResource.count() == 1

        params.id = contentResource.id

        controller.delete()

        assert ContentResource.count() == 0
        assert ContentResource.get(contentResource.id) == null
        assert response.redirectedUrl == '/contentResource/list'
    }
}
