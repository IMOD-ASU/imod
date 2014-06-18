package imodv6



import org.junit.*
import grails.test.mixin.*

@TestFor(ActionWordController)
@Mock(ActionWord)
class ActionWordControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/actionWord/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.actionWordInstanceList.size() == 0
        assert model.actionWordInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.actionWordInstance != null
    }

    void testSave() {
        controller.save()

        assert model.actionWordInstance != null
        assert view == '/actionWord/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/actionWord/show/1'
        assert controller.flash.message != null
        assert ActionWord.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/actionWord/list'

        populateValidParams(params)
        def actionWord = new ActionWord(params)

        assert actionWord.save() != null

        params.id = actionWord.id

        def model = controller.show()

        assert model.actionWordInstance == actionWord
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/actionWord/list'

        populateValidParams(params)
        def actionWord = new ActionWord(params)

        assert actionWord.save() != null

        params.id = actionWord.id

        def model = controller.edit()

        assert model.actionWordInstance == actionWord
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/actionWord/list'

        response.reset()

        populateValidParams(params)
        def actionWord = new ActionWord(params)

        assert actionWord.save() != null

        // test invalid parameters in update
        params.id = actionWord.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/actionWord/edit"
        assert model.actionWordInstance != null

        actionWord.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/actionWord/show/$actionWord.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        actionWord.clearErrors()

        populateValidParams(params)
        params.id = actionWord.id
        params.version = -1
        controller.update()

        assert view == "/actionWord/edit"
        assert model.actionWordInstance != null
        assert model.actionWordInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/actionWord/list'

        response.reset()

        populateValidParams(params)
        def actionWord = new ActionWord(params)

        assert actionWord.save() != null
        assert ActionWord.count() == 1

        params.id = actionWord.id

        controller.delete()

        assert ActionWord.count() == 0
        assert ActionWord.get(actionWord.id) == null
        assert response.redirectedUrl == '/actionWord/list'
    }
}
