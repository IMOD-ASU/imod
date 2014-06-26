package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(CoursePolicyController)
@Mock(CoursePolicy)
class CoursePolicyControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/coursePolicy/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.coursePolicyInstanceList.size() == 0
        assert model.coursePolicyInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.coursePolicyInstance != null
    }

    void testSave() {
        controller.save()

        assert model.coursePolicyInstance != null
        assert view == '/coursePolicy/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/coursePolicy/show/1'
        assert controller.flash.message != null
        assert CoursePolicy.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/coursePolicy/list'

        populateValidParams(params)
        def coursePolicy = new CoursePolicy(params)

        assert coursePolicy.save() != null

        params.id = coursePolicy.id

        def model = controller.show()

        assert model.coursePolicyInstance == coursePolicy
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/coursePolicy/list'

        populateValidParams(params)
        def coursePolicy = new CoursePolicy(params)

        assert coursePolicy.save() != null

        params.id = coursePolicy.id

        def model = controller.edit()

        assert model.coursePolicyInstance == coursePolicy
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/coursePolicy/list'

        response.reset()

        populateValidParams(params)
        def coursePolicy = new CoursePolicy(params)

        assert coursePolicy.save() != null

        // test invalid parameters in update
        params.id = coursePolicy.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/coursePolicy/edit"
        assert model.coursePolicyInstance != null

        coursePolicy.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/coursePolicy/show/$coursePolicy.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        coursePolicy.clearErrors()

        populateValidParams(params)
        params.id = coursePolicy.id
        params.version = -1
        controller.update()

        assert view == "/coursePolicy/edit"
        assert model.coursePolicyInstance != null
        assert model.coursePolicyInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/coursePolicy/list'

        response.reset()

        populateValidParams(params)
        def coursePolicy = new CoursePolicy(params)

        assert coursePolicy.save() != null
        assert CoursePolicy.count() == 1

        params.id = coursePolicy.id

        controller.delete()

        assert CoursePolicy.count() == 0
        assert CoursePolicy.get(coursePolicy.id) == null
        assert response.redirectedUrl == '/coursePolicy/list'
    }
}
