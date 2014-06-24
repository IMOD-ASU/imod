package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(LearningDomainController)
@Mock(LearningDomain)
class LearningDomainControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/learningDomain/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.learningDomainInstanceList.size() == 0
        assert model.learningDomainInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.learningDomainInstance != null
    }

    void testSave() {
        controller.save()

        assert model.learningDomainInstance != null
        assert view == '/learningDomain/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/learningDomain/show/1'
        assert controller.flash.message != null
        assert LearningDomain.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/learningDomain/list'

        populateValidParams(params)
        def learningDomain = new LearningDomain(params)

        assert learningDomain.save() != null

        params.id = learningDomain.id

        def model = controller.show()

        assert model.learningDomainInstance == learningDomain
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/learningDomain/list'

        populateValidParams(params)
        def learningDomain = new LearningDomain(params)

        assert learningDomain.save() != null

        params.id = learningDomain.id

        def model = controller.edit()

        assert model.learningDomainInstance == learningDomain
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/learningDomain/list'

        response.reset()

        populateValidParams(params)
        def learningDomain = new LearningDomain(params)

        assert learningDomain.save() != null

        // test invalid parameters in update
        params.id = learningDomain.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/learningDomain/edit"
        assert model.learningDomainInstance != null

        learningDomain.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/learningDomain/show/$learningDomain.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        learningDomain.clearErrors()

        populateValidParams(params)
        params.id = learningDomain.id
        params.version = -1
        controller.update()

        assert view == "/learningDomain/edit"
        assert model.learningDomainInstance != null
        assert model.learningDomainInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/learningDomain/list'

        response.reset()

        populateValidParams(params)
        def learningDomain = new LearningDomain(params)

        assert learningDomain.save() != null
        assert LearningDomain.count() == 1

        params.id = learningDomain.id

        controller.delete()

        assert LearningDomain.count() == 0
        assert LearningDomain.get(learningDomain.id) == null
        assert response.redirectedUrl == '/learningDomain/list'
    }
}
