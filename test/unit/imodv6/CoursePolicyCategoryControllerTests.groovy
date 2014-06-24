package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(CoursePolicyCategoryController)
@Mock(CoursePolicyCategory)
class CoursePolicyCategoryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/coursePolicyCategory/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.coursePolicyCategoryInstanceList.size() == 0
        assert model.coursePolicyCategoryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.coursePolicyCategoryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.coursePolicyCategoryInstance != null
        assert view == '/coursePolicyCategory/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/coursePolicyCategory/show/1'
        assert controller.flash.message != null
        assert CoursePolicyCategory.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/coursePolicyCategory/list'

        populateValidParams(params)
        def coursePolicyCategory = new CoursePolicyCategory(params)

        assert coursePolicyCategory.save() != null

        params.id = coursePolicyCategory.id

        def model = controller.show()

        assert model.coursePolicyCategoryInstance == coursePolicyCategory
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/coursePolicyCategory/list'

        populateValidParams(params)
        def coursePolicyCategory = new CoursePolicyCategory(params)

        assert coursePolicyCategory.save() != null

        params.id = coursePolicyCategory.id

        def model = controller.edit()

        assert model.coursePolicyCategoryInstance == coursePolicyCategory
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/coursePolicyCategory/list'

        response.reset()

        populateValidParams(params)
        def coursePolicyCategory = new CoursePolicyCategory(params)

        assert coursePolicyCategory.save() != null

        // test invalid parameters in update
        params.id = coursePolicyCategory.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/coursePolicyCategory/edit"
        assert model.coursePolicyCategoryInstance != null

        coursePolicyCategory.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/coursePolicyCategory/show/$coursePolicyCategory.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        coursePolicyCategory.clearErrors()

        populateValidParams(params)
        params.id = coursePolicyCategory.id
        params.version = -1
        controller.update()

        assert view == "/coursePolicyCategory/edit"
        assert model.coursePolicyCategoryInstance != null
        assert model.coursePolicyCategoryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/coursePolicyCategory/list'

        response.reset()

        populateValidParams(params)
        def coursePolicyCategory = new CoursePolicyCategory(params)

        assert coursePolicyCategory.save() != null
        assert CoursePolicyCategory.count() == 1

        params.id = coursePolicyCategory.id

        controller.delete()

        assert CoursePolicyCategory.count() == 0
        assert CoursePolicyCategory.get(coursePolicyCategory.id) == null
        assert response.redirectedUrl == '/coursePolicyCategory/list'
    }
}
