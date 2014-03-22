package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(ContentPriorityCodeController)
@Mock(ContentPriorityCode)
class ContentPriorityCodeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/contentPriorityCode/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.contentPriorityCodeInstanceList.size() == 0
        assert model.contentPriorityCodeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.contentPriorityCodeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.contentPriorityCodeInstance != null
        assert view == '/contentPriorityCode/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/contentPriorityCode/show/1'
        assert controller.flash.message != null
        assert ContentPriorityCode.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/contentPriorityCode/list'

        populateValidParams(params)
        def contentPriorityCode = new ContentPriorityCode(params)

        assert contentPriorityCode.save() != null

        params.id = contentPriorityCode.id

        def model = controller.show()

        assert model.contentPriorityCodeInstance == contentPriorityCode
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/contentPriorityCode/list'

        populateValidParams(params)
        def contentPriorityCode = new ContentPriorityCode(params)

        assert contentPriorityCode.save() != null

        params.id = contentPriorityCode.id

        def model = controller.edit()

        assert model.contentPriorityCodeInstance == contentPriorityCode
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/contentPriorityCode/list'

        response.reset()

        populateValidParams(params)
        def contentPriorityCode = new ContentPriorityCode(params)

        assert contentPriorityCode.save() != null

        // test invalid parameters in update
        params.id = contentPriorityCode.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/contentPriorityCode/edit"
        assert model.contentPriorityCodeInstance != null

        contentPriorityCode.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/contentPriorityCode/show/$contentPriorityCode.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        contentPriorityCode.clearErrors()

        populateValidParams(params)
        params.id = contentPriorityCode.id
        params.version = -1
        controller.update()

        assert view == "/contentPriorityCode/edit"
        assert model.contentPriorityCodeInstance != null
        assert model.contentPriorityCodeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/contentPriorityCode/list'

        response.reset()

        populateValidParams(params)
        def contentPriorityCode = new ContentPriorityCode(params)

        assert contentPriorityCode.save() != null
        assert ContentPriorityCode.count() == 1

        params.id = contentPriorityCode.id

        controller.delete()

        assert ContentPriorityCode.count() == 0
        assert ContentPriorityCode.get(contentPriorityCode.id) == null
        assert response.redirectedUrl == '/contentPriorityCode/list'
    }
}
