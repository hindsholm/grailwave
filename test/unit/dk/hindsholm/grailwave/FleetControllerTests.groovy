package dk.hindsholm.grailwave



import org.junit.*
import grails.test.mixin.*

@TestFor(FleetController)
@Mock(Fleet)
class FleetControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/fleet/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.fleetInstanceList.size() == 0
        assert model.fleetInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.fleetInstance != null
    }

    void testSave() {
        controller.save()

        assert model.fleetInstance != null
        assert view == '/fleet/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/fleet/show/1'
        assert controller.flash.message != null
        assert Fleet.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/fleet/list'

        populateValidParams(params)
        def fleet = new Fleet(params)

        assert fleet.save() != null

        params.id = fleet.id

        def model = controller.show()

        assert model.fleetInstance == fleet
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/fleet/list'

        populateValidParams(params)
        def fleet = new Fleet(params)

        assert fleet.save() != null

        params.id = fleet.id

        def model = controller.edit()

        assert model.fleetInstance == fleet
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/fleet/list'

        response.reset()

        populateValidParams(params)
        def fleet = new Fleet(params)

        assert fleet.save() != null

        // test invalid parameters in update
        params.id = fleet.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/fleet/edit"
        assert model.fleetInstance != null

        fleet.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/fleet/show/$fleet.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        fleet.clearErrors()

        populateValidParams(params)
        params.id = fleet.id
        params.version = -1
        controller.update()

        assert view == "/fleet/edit"
        assert model.fleetInstance != null
        assert model.fleetInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/fleet/list'

        response.reset()

        populateValidParams(params)
        def fleet = new Fleet(params)

        assert fleet.save() != null
        assert Fleet.count() == 1

        params.id = fleet.id

        controller.delete()

        assert Fleet.count() == 0
        assert Fleet.get(fleet.id) == null
        assert response.redirectedUrl == '/fleet/list'
    }
}
