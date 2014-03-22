package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(DomainCategoryController)
@Mock(DomainCategory)
class DomainCategoryControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/domainCategory/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.domainCategoryInstanceList.size() == 0
        assert model.domainCategoryInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.domainCategoryInstance != null
    }

    void testSave() {
        controller.save()

        assert model.domainCategoryInstance != null
        assert view == '/domainCategory/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/domainCategory/show/1'
        assert controller.flash.message != null
        assert DomainCategory.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/domainCategory/list'

        populateValidParams(params)
        def domainCategory = new DomainCategory(params)

        assert domainCategory.save() != null

        params.id = domainCategory.id

        def model = controller.show()

        assert model.domainCategoryInstance == domainCategory
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/domainCategory/list'

        populateValidParams(params)
        def domainCategory = new DomainCategory(params)

        assert domainCategory.save() != null

        params.id = domainCategory.id

        def model = controller.edit()

        assert model.domainCategoryInstance == domainCategory
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/domainCategory/list'

        response.reset()

        populateValidParams(params)
        def domainCategory = new DomainCategory(params)

        assert domainCategory.save() != null

        // test invalid parameters in update
        params.id = domainCategory.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/domainCategory/edit"
        assert model.domainCategoryInstance != null

        domainCategory.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/domainCategory/show/$domainCategory.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        domainCategory.clearErrors()

        populateValidParams(params)
        params.id = domainCategory.id
        params.version = -1
        controller.update()

        assert view == "/domainCategory/edit"
        assert model.domainCategoryInstance != null
        assert model.domainCategoryInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/domainCategory/list'

        response.reset()

        populateValidParams(params)
        def domainCategory = new DomainCategory(params)

        assert domainCategory.save() != null
        assert DomainCategory.count() == 1

        params.id = domainCategory.id

        controller.delete()

        assert DomainCategory.count() == 0
        assert DomainCategory.get(domainCategory.id) == null
        assert response.redirectedUrl == '/domainCategory/list'
    }
}
