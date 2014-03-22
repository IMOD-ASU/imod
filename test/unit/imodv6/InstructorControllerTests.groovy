package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(InstructorController)
@Mock(Instructor)
class InstructorControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/instructor/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.instructorInstanceList.size() == 0
        assert model.instructorInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.instructorInstance != null
    }

    void testSave() {
        controller.save()

        assert model.instructorInstance != null
        assert view == '/instructor/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/instructor/show/1'
        assert controller.flash.message != null
        assert Instructor.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/instructor/list'

        populateValidParams(params)
        def instructor = new Instructor(params)

        assert instructor.save() != null

        params.id = instructor.id

        def model = controller.show()

        assert model.instructorInstance == instructor
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/instructor/list'

        populateValidParams(params)
        def instructor = new Instructor(params)

        assert instructor.save() != null

        params.id = instructor.id

        def model = controller.edit()

        assert model.instructorInstance == instructor
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/instructor/list'

        response.reset()

        populateValidParams(params)
        def instructor = new Instructor(params)

        assert instructor.save() != null

        // test invalid parameters in update
        params.id = instructor.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/instructor/edit"
        assert model.instructorInstance != null

        instructor.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/instructor/show/$instructor.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        instructor.clearErrors()

        populateValidParams(params)
        params.id = instructor.id
        params.version = -1
        controller.update()

        assert view == "/instructor/edit"
        assert model.instructorInstance != null
        assert model.instructorInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/instructor/list'

        response.reset()

        populateValidParams(params)
        def instructor = new Instructor(params)

        assert instructor.save() != null
        assert Instructor.count() == 1

        params.id = instructor.id

        controller.delete()

        assert Instructor.count() == 0
        assert Instructor.get(instructor.id) == null
        assert response.redirectedUrl == '/instructor/list'
    }
}
