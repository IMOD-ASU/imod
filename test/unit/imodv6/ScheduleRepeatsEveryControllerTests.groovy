package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(ScheduleRepeatsEveryController)
@Mock(ScheduleRepeatsEvery)
class ScheduleRepeatsEveryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/scheduleRepeatsEvery/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.scheduleRepeatsEveryInstanceList.size() == 0
        assert model.scheduleRepeatsEveryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.scheduleRepeatsEveryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.scheduleRepeatsEveryInstance != null
        assert view == '/scheduleRepeatsEvery/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/scheduleRepeatsEvery/show/1'
        assert controller.flash.message != null
        assert ScheduleRepeatsEvery.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/scheduleRepeatsEvery/list'

        populateValidParams(params)
        def scheduleRepeatsEvery = new ScheduleRepeatsEvery(params)

        assert scheduleRepeatsEvery.save() != null

        params.id = scheduleRepeatsEvery.id

        def model = controller.show()

        assert model.scheduleRepeatsEveryInstance == scheduleRepeatsEvery
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/scheduleRepeatsEvery/list'

        populateValidParams(params)
        def scheduleRepeatsEvery = new ScheduleRepeatsEvery(params)

        assert scheduleRepeatsEvery.save() != null

        params.id = scheduleRepeatsEvery.id

        def model = controller.edit()

        assert model.scheduleRepeatsEveryInstance == scheduleRepeatsEvery
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/scheduleRepeatsEvery/list'

        response.reset()

        populateValidParams(params)
        def scheduleRepeatsEvery = new ScheduleRepeatsEvery(params)

        assert scheduleRepeatsEvery.save() != null

        // test invalid parameters in update
        params.id = scheduleRepeatsEvery.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/scheduleRepeatsEvery/edit"
        assert model.scheduleRepeatsEveryInstance != null

        scheduleRepeatsEvery.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/scheduleRepeatsEvery/show/$scheduleRepeatsEvery.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        scheduleRepeatsEvery.clearErrors()

        populateValidParams(params)
        params.id = scheduleRepeatsEvery.id
        params.version = -1
        controller.update()

        assert view == "/scheduleRepeatsEvery/edit"
        assert model.scheduleRepeatsEveryInstance != null
        assert model.scheduleRepeatsEveryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/scheduleRepeatsEvery/list'

        response.reset()

        populateValidParams(params)
        def scheduleRepeatsEvery = new ScheduleRepeatsEvery(params)

        assert scheduleRepeatsEvery.save() != null
        assert ScheduleRepeatsEvery.count() == 1

        params.id = scheduleRepeatsEvery.id

        controller.delete()

        assert ScheduleRepeatsEvery.count() == 0
        assert ScheduleRepeatsEvery.get(scheduleRepeatsEvery.id) == null
        assert response.redirectedUrl == '/scheduleRepeatsEvery/list'
    }
}
