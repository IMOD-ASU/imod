package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(ScheduleController)
@Mock(Schedule)
class ScheduleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/schedule/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.scheduleInstanceList.size() == 0
        assert model.scheduleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.scheduleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.scheduleInstance != null
        assert view == '/schedule/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/schedule/show/1'
        assert controller.flash.message != null
        assert Schedule.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/schedule/list'

        populateValidParams(params)
        def schedule = new Schedule(params)

        assert schedule.save() != null

        params.id = schedule.id

        def model = controller.show()

        assert model.scheduleInstance == schedule
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/schedule/list'

        populateValidParams(params)
        def schedule = new Schedule(params)

        assert schedule.save() != null

        params.id = schedule.id

        def model = controller.edit()

        assert model.scheduleInstance == schedule
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/schedule/list'

        response.reset()

        populateValidParams(params)
        def schedule = new Schedule(params)

        assert schedule.save() != null

        // test invalid parameters in update
        params.id = schedule.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/schedule/edit"
        assert model.scheduleInstance != null

        schedule.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/schedule/show/$schedule.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        schedule.clearErrors()

        populateValidParams(params)
        params.id = schedule.id
        params.version = -1
        controller.update()

        assert view == "/schedule/edit"
        assert model.scheduleInstance != null
        assert model.scheduleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/schedule/list'

        response.reset()

        populateValidParams(params)
        def schedule = new Schedule(params)

        assert schedule.save() != null
        assert Schedule.count() == 1

        params.id = schedule.id

        controller.delete()

        assert Schedule.count() == 0
        assert Schedule.get(schedule.id) == null
        assert response.redirectedUrl == '/schedule/list'
    }
}
