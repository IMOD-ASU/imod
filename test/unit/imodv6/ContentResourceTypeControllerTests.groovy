package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(ContentResourceTypeController)
@Mock(ContentResourceType)
class ContentResourceTypeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/contentResourceType/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.contentResourceTypeInstanceList.size() == 0
        assert model.contentResourceTypeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.contentResourceTypeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.contentResourceTypeInstance != null
        assert view == '/contentResourceType/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/contentResourceType/show/1'
        assert controller.flash.message != null
        assert ContentResourceType.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/contentResourceType/list'

        populateValidParams(params)
        def contentResourceType = new ContentResourceType(params)

        assert contentResourceType.save() != null

        params.id = contentResourceType.id

        def model = controller.show()

        assert model.contentResourceTypeInstance == contentResourceType
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/contentResourceType/list'

        populateValidParams(params)
        def contentResourceType = new ContentResourceType(params)

        assert contentResourceType.save() != null

        params.id = contentResourceType.id

        def model = controller.edit()

        assert model.contentResourceTypeInstance == contentResourceType
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/contentResourceType/list'

        response.reset()

        populateValidParams(params)
        def contentResourceType = new ContentResourceType(params)

        assert contentResourceType.save() != null

        // test invalid parameters in update
        params.id = contentResourceType.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/contentResourceType/edit"
        assert model.contentResourceTypeInstance != null

        contentResourceType.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/contentResourceType/show/$contentResourceType.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        contentResourceType.clearErrors()

        populateValidParams(params)
        params.id = contentResourceType.id
        params.version = -1
        controller.update()

        assert view == "/contentResourceType/edit"
        assert model.contentResourceTypeInstance != null
        assert model.contentResourceTypeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/contentResourceType/list'

        response.reset()

        populateValidParams(params)
        def contentResourceType = new ContentResourceType(params)

        assert contentResourceType.save() != null
        assert ContentResourceType.count() == 1

        params.id = contentResourceType.id

        controller.delete()

        assert ContentResourceType.count() == 0
        assert ContentResourceType.get(contentResourceType.id) == null
        assert response.redirectedUrl == '/contentResourceType/list'
    }
}
