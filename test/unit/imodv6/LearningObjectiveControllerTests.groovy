package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(LearningObjectiveController)
@Mock(LearningObjective)
class LearningObjectiveControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/learningObjective/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.learningObjectiveInstanceList.size() == 0
        assert model.learningObjectiveInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.learningObjectiveInstance != null
    }

    void testSave() {
        controller.save()

        assert model.learningObjectiveInstance != null
        assert view == '/learningObjective/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/learningObjective/show/1'
        assert controller.flash.message != null
        assert LearningObjective.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/learningObjective/list'

        populateValidParams(params)
        def learningObjective = new LearningObjective(params)

        assert learningObjective.save() != null

        params.id = learningObjective.id

        def model = controller.show()

        assert model.learningObjectiveInstance == learningObjective
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/learningObjective/list'

        populateValidParams(params)
        def learningObjective = new LearningObjective(params)

        assert learningObjective.save() != null

        params.id = learningObjective.id

        def model = controller.edit()

        assert model.learningObjectiveInstance == learningObjective
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/learningObjective/list'

        response.reset()

        populateValidParams(params)
        def learningObjective = new LearningObjective(params)

        assert learningObjective.save() != null

        // test invalid parameters in update
        params.id = learningObjective.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/learningObjective/edit"
        assert model.learningObjectiveInstance != null

        learningObjective.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/learningObjective/show/$learningObjective.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        learningObjective.clearErrors()

        populateValidParams(params)
        params.id = learningObjective.id
        params.version = -1
        controller.update()

        assert view == "/learningObjective/edit"
        assert model.learningObjectiveInstance != null
        assert model.learningObjectiveInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/learningObjective/list'

        response.reset()

        populateValidParams(params)
        def learningObjective = new LearningObjective(params)

        assert learningObjective.save() != null
        assert LearningObjective.count() == 1

        params.id = learningObjective.id

        controller.delete()

        assert LearningObjective.count() == 0
        assert LearningObjective.get(learningObjective.id) == null
        assert response.redirectedUrl == '/learningObjective/list'
    }
}
