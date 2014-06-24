package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(ContentController)
@Mock(Content)
class ContentControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/content/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.contentInstanceList.size() == 0
        assert model.contentInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.contentInstance != null
    }

    void testSave() {
        controller.save()

        assert model.contentInstance != null
        assert view == '/content/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/content/show/1'
        assert controller.flash.message != null
        assert Content.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/content/list'

        populateValidParams(params)
        def content = new Content(params)

        assert content.save() != null

        params.id = content.id

        def model = controller.show()

        assert model.contentInstance == content
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/content/list'

        populateValidParams(params)
        def content = new Content(params)

        assert content.save() != null

        params.id = content.id

        def model = controller.edit()

        assert model.contentInstance == content
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/content/list'

        response.reset()

        populateValidParams(params)
        def content = new Content(params)

        assert content.save() != null

        // test invalid parameters in update
        params.id = content.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/content/edit"
        assert model.contentInstance != null

        content.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/content/show/$content.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        content.clearErrors()

        populateValidParams(params)
        params.id = content.id
        params.version = -1
        controller.update()

        assert view == "/content/edit"
        assert model.contentInstance != null
        assert model.contentInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/content/list'

        response.reset()

        populateValidParams(params)
        def content = new Content(params)

        assert content.save() != null
        assert Content.count() == 1

        params.id = content.id

        controller.delete()

        assert Content.count() == 0
        assert Content.get(content.id) == null
        assert response.redirectedUrl == '/content/list'
    }
}
