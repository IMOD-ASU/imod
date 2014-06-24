package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(ImodController)
@Mock(Imod)
class ImodControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/imod/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.imodInstanceList.size() == 0
        assert model.imodInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.imodInstance != null
    }

    void testSave() {
        controller.save()

        assert model.imodInstance != null
        assert view == '/imod/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/imod/show/1'
        assert controller.flash.message != null
        assert Imod.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/imod/list'

        populateValidParams(params)
        def imod = new Imod(params)

        assert imod.save() != null

        params.id = imod.id

        def model = controller.show()

        assert model.imodInstance == imod
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/imod/list'

        populateValidParams(params)
        def imod = new Imod(params)

        assert imod.save() != null

        params.id = imod.id

        def model = controller.edit()

        assert model.imodInstance == imod
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/imod/list'

        response.reset()

        populateValidParams(params)
        def imod = new Imod(params)

        assert imod.save() != null

        // test invalid parameters in update
        params.id = imod.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/imod/edit"
        assert model.imodInstance != null

        imod.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/imod/show/$imod.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        imod.clearErrors()

        populateValidParams(params)
        params.id = imod.id
        params.version = -1
        controller.update()

        assert view == "/imod/edit"
        assert model.imodInstance != null
        assert model.imodInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/imod/list'

        response.reset()

        populateValidParams(params)
        def imod = new Imod(params)

        assert imod.save() != null
        assert Imod.count() == 1

        params.id = imod.id

        controller.delete()

        assert Imod.count() == 0
        assert Imod.get(imod.id) == null
        assert response.redirectedUrl == '/imod/list'
    }
}
