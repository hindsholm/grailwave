package dk.hindsholm.grailwave



import org.junit.*
import grails.test.mixin.*

@TestFor(BoatController)
@Mock(Boat)
class BoatControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/boat/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.boatInstanceList.size() == 0
        assert model.boatInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.boatInstance != null
    }

    void testSave() {
        controller.save()

        assert model.boatInstance != null
        assert view == '/boat/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/boat/show/1'
        assert controller.flash.message != null
        assert Boat.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/boat/list'

        populateValidParams(params)
        def boat = new Boat(params)

        assert boat.save() != null

        params.id = boat.id

        def model = controller.show()

        assert model.boatInstance == boat
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/boat/list'

        populateValidParams(params)
        def boat = new Boat(params)

        assert boat.save() != null

        params.id = boat.id

        def model = controller.edit()

        assert model.boatInstance == boat
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/boat/list'

        response.reset()

        populateValidParams(params)
        def boat = new Boat(params)

        assert boat.save() != null

        // test invalid parameters in update
        params.id = boat.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/boat/edit"
        assert model.boatInstance != null

        boat.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/boat/show/$boat.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        boat.clearErrors()

        populateValidParams(params)
        params.id = boat.id
        params.version = -1
        controller.update()

        assert view == "/boat/edit"
        assert model.boatInstance != null
        assert model.boatInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/boat/list'

        response.reset()

        populateValidParams(params)
        def boat = new Boat(params)

        assert boat.save() != null
        assert Boat.count() == 1

        params.id = boat.id

        controller.delete()

        assert Boat.count() == 0
        assert Boat.get(boat.id) == null
        assert response.redirectedUrl == '/boat/list'
    }
}
