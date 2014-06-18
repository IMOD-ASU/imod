package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(InstructorPhoneController)
@Mock(InstructorPhone)
class InstructorPhoneControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/instructorPhone/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.instructorPhoneInstanceList.size() == 0
        assert model.instructorPhoneInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.instructorPhoneInstance != null
    }

    void testSave() {
        controller.save()

        assert model.instructorPhoneInstance != null
        assert view == '/instructorPhone/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/instructorPhone/show/1'
        assert controller.flash.message != null
        assert InstructorPhone.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/instructorPhone/list'

        populateValidParams(params)
        def instructorPhone = new InstructorPhone(params)

        assert instructorPhone.save() != null

        params.id = instructorPhone.id

        def model = controller.show()

        assert model.instructorPhoneInstance == instructorPhone
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/instructorPhone/list'

        populateValidParams(params)
        def instructorPhone = new InstructorPhone(params)

        assert instructorPhone.save() != null

        params.id = instructorPhone.id

        def model = controller.edit()

        assert model.instructorPhoneInstance == instructorPhone
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/instructorPhone/list'

        response.reset()

        populateValidParams(params)
        def instructorPhone = new InstructorPhone(params)

        assert instructorPhone.save() != null

        // test invalid parameters in update
        params.id = instructorPhone.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/instructorPhone/edit"
        assert model.instructorPhoneInstance != null

        instructorPhone.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/instructorPhone/show/$instructorPhone.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        instructorPhone.clearErrors()

        populateValidParams(params)
        params.id = instructorPhone.id
        params.version = -1
        controller.update()

        assert view == "/instructorPhone/edit"
        assert model.instructorPhoneInstance != null
        assert model.instructorPhoneInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/instructorPhone/list'

        response.reset()

        populateValidParams(params)
        def instructorPhone = new InstructorPhone(params)

        assert instructorPhone.save() != null
        assert InstructorPhone.count() == 1

        params.id = instructorPhone.id

        controller.delete()

        assert InstructorPhone.count() == 0
        assert InstructorPhone.get(instructorPhone.id) == null
        assert response.redirectedUrl == '/instructorPhone/list'
    }
}
