package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(ComponentDesignationController)
@Mock(ComponentDesignation)
class ComponentDesignationControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/componentDesignation/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.componentDesignationInstanceList.size() == 0
        assert model.componentDesignationInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.componentDesignationInstance != null
    }

    void testSave() {
        controller.save()

        assert model.componentDesignationInstance != null
        assert view == '/componentDesignation/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/componentDesignation/show/1'
        assert controller.flash.message != null
        assert ComponentDesignation.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/componentDesignation/list'

        populateValidParams(params)
        def componentDesignation = new ComponentDesignation(params)

        assert componentDesignation.save() != null

        params.id = componentDesignation.id

        def model = controller.show()

        assert model.componentDesignationInstance == componentDesignation
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/componentDesignation/list'

        populateValidParams(params)
        def componentDesignation = new ComponentDesignation(params)

        assert componentDesignation.save() != null

        params.id = componentDesignation.id

        def model = controller.edit()

        assert model.componentDesignationInstance == componentDesignation
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/componentDesignation/list'

        response.reset()

        populateValidParams(params)
        def componentDesignation = new ComponentDesignation(params)

        assert componentDesignation.save() != null

        // test invalid parameters in update
        params.id = componentDesignation.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/componentDesignation/edit"
        assert model.componentDesignationInstance != null

        componentDesignation.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/componentDesignation/show/$componentDesignation.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        componentDesignation.clearErrors()

        populateValidParams(params)
        params.id = componentDesignation.id
        params.version = -1
        controller.update()

        assert view == "/componentDesignation/edit"
        assert model.componentDesignationInstance != null
        assert model.componentDesignationInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/componentDesignation/list'

        response.reset()

        populateValidParams(params)
        def componentDesignation = new ComponentDesignation(params)

        assert componentDesignation.save() != null
        assert ComponentDesignation.count() == 1

        params.id = componentDesignation.id

        controller.delete()

        assert ComponentDesignation.count() == 0
        assert ComponentDesignation.get(componentDesignation.id) == null
        assert response.redirectedUrl == '/componentDesignation/list'
    }
}
