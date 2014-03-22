package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(CourseComponentCodeController)
@Mock(CourseComponentCode)
class CourseComponentCodeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/courseComponentCode/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.courseComponentCodeInstanceList.size() == 0
        assert model.courseComponentCodeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.courseComponentCodeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.courseComponentCodeInstance != null
        assert view == '/courseComponentCode/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/courseComponentCode/show/1'
        assert controller.flash.message != null
        assert CourseComponentCode.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/courseComponentCode/list'

        populateValidParams(params)
        def courseComponentCode = new CourseComponentCode(params)

        assert courseComponentCode.save() != null

        params.id = courseComponentCode.id

        def model = controller.show()

        assert model.courseComponentCodeInstance == courseComponentCode
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/courseComponentCode/list'

        populateValidParams(params)
        def courseComponentCode = new CourseComponentCode(params)

        assert courseComponentCode.save() != null

        params.id = courseComponentCode.id

        def model = controller.edit()

        assert model.courseComponentCodeInstance == courseComponentCode
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/courseComponentCode/list'

        response.reset()

        populateValidParams(params)
        def courseComponentCode = new CourseComponentCode(params)

        assert courseComponentCode.save() != null

        // test invalid parameters in update
        params.id = courseComponentCode.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/courseComponentCode/edit"
        assert model.courseComponentCodeInstance != null

        courseComponentCode.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/courseComponentCode/show/$courseComponentCode.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        courseComponentCode.clearErrors()

        populateValidParams(params)
        params.id = courseComponentCode.id
        params.version = -1
        controller.update()

        assert view == "/courseComponentCode/edit"
        assert model.courseComponentCodeInstance != null
        assert model.courseComponentCodeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/courseComponentCode/list'

        response.reset()

        populateValidParams(params)
        def courseComponentCode = new CourseComponentCode(params)

        assert courseComponentCode.save() != null
        assert CourseComponentCode.count() == 1

        params.id = courseComponentCode.id

        controller.delete()

        assert CourseComponentCode.count() == 0
        assert CourseComponentCode.get(courseComponentCode.id) == null
        assert response.redirectedUrl == '/courseComponentCode/list'
    }
}
