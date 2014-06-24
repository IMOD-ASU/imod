package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(AudienceController)
@Mock(Audience)
class AudienceControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/audience/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.audienceInstanceList.size() == 0
        assert model.audienceInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.audienceInstance != null
    }

    void testSave() {
        controller.save()

        assert model.audienceInstance != null
        assert view == '/audience/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/audience/show/1'
        assert controller.flash.message != null
        assert Audience.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/audience/list'

        populateValidParams(params)
        def audience = new Audience(params)

        assert audience.save() != null

        params.id = audience.id

        def model = controller.show()

        assert model.audienceInstance == audience
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/audience/list'

        populateValidParams(params)
        def audience = new Audience(params)

        assert audience.save() != null

        params.id = audience.id

        def model = controller.edit()

        assert model.audienceInstance == audience
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/audience/list'

        response.reset()

        populateValidParams(params)
        def audience = new Audience(params)

        assert audience.save() != null

        // test invalid parameters in update
        params.id = audience.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/audience/edit"
        assert model.audienceInstance != null

        audience.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/audience/show/$audience.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        audience.clearErrors()

        populateValidParams(params)
        params.id = audience.id
        params.version = -1
        controller.update()

        assert view == "/audience/edit"
        assert model.audienceInstance != null
        assert model.audienceInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/audience/list'

        response.reset()

        populateValidParams(params)
        def audience = new Audience(params)

        assert audience.save() != null
        assert Audience.count() == 1

        params.id = audience.id

        controller.delete()

        assert Audience.count() == 0
        assert Audience.get(audience.id) == null
        assert response.redirectedUrl == '/audience/list'
    }
}
