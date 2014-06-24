package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(ScheduleRepeatsController)
@Mock(ScheduleRepeats)
class ScheduleRepeatsControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/scheduleRepeats/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.scheduleRepeatsInstanceList.size() == 0
        assert model.scheduleRepeatsInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.scheduleRepeatsInstance != null
    }

    void testSave() {
        controller.save()

        assert model.scheduleRepeatsInstance != null
        assert view == '/scheduleRepeats/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/scheduleRepeats/show/1'
        assert controller.flash.message != null
        assert ScheduleRepeats.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/scheduleRepeats/list'

        populateValidParams(params)
        def scheduleRepeats = new ScheduleRepeats(params)

        assert scheduleRepeats.save() != null

        params.id = scheduleRepeats.id

        def model = controller.show()

        assert model.scheduleRepeatsInstance == scheduleRepeats
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/scheduleRepeats/list'

        populateValidParams(params)
        def scheduleRepeats = new ScheduleRepeats(params)

        assert scheduleRepeats.save() != null

        params.id = scheduleRepeats.id

        def model = controller.edit()

        assert model.scheduleRepeatsInstance == scheduleRepeats
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/scheduleRepeats/list'

        response.reset()

        populateValidParams(params)
        def scheduleRepeats = new ScheduleRepeats(params)

        assert scheduleRepeats.save() != null

        // test invalid parameters in update
        params.id = scheduleRepeats.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/scheduleRepeats/edit"
        assert model.scheduleRepeatsInstance != null

        scheduleRepeats.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/scheduleRepeats/show/$scheduleRepeats.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        scheduleRepeats.clearErrors()

        populateValidParams(params)
        params.id = scheduleRepeats.id
        params.version = -1
        controller.update()

        assert view == "/scheduleRepeats/edit"
        assert model.scheduleRepeatsInstance != null
        assert model.scheduleRepeatsInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/scheduleRepeats/list'

        response.reset()

        populateValidParams(params)
        def scheduleRepeats = new ScheduleRepeats(params)

        assert scheduleRepeats.save() != null
        assert ScheduleRepeats.count() == 1

        params.id = scheduleRepeats.id

        controller.delete()

        assert ScheduleRepeats.count() == 0
        assert ScheduleRepeats.get(scheduleRepeats.id) == null
        assert response.redirectedUrl == '/scheduleRepeats/list'
    }
}
