package dk.hindsholm.grailwave



import org.junit.*
import grails.test.mixin.*

@TestFor(SeriesController)
@Mock(Series)
class SeriesControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/series/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.seriesInstanceList.size() == 0
        assert model.seriesInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.seriesInstance != null
    }

    void testSave() {
        controller.save()

        assert model.seriesInstance != null
        assert view == '/series/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/series/show/1'
        assert controller.flash.message != null
        assert Series.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/series/list'

        populateValidParams(params)
        def series = new Series(params)

        assert series.save() != null

        params.id = series.id

        def model = controller.show()

        assert model.seriesInstance == series
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/series/list'

        populateValidParams(params)
        def series = new Series(params)

        assert series.save() != null

        params.id = series.id

        def model = controller.edit()

        assert model.seriesInstance == series
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/series/list'

        response.reset()

        populateValidParams(params)
        def series = new Series(params)

        assert series.save() != null

        // test invalid parameters in update
        params.id = series.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/series/edit"
        assert model.seriesInstance != null

        series.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/series/show/$series.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        series.clearErrors()

        populateValidParams(params)
        params.id = series.id
        params.version = -1
        controller.update()

        assert view == "/series/edit"
        assert model.seriesInstance != null
        assert model.seriesInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/series/list'

        response.reset()

        populateValidParams(params)
        def series = new Series(params)

        assert series.save() != null
        assert Series.count() == 1

        params.id = series.id

        controller.delete()

        assert Series.count() == 0
        assert Series.get(series.id) == null
        assert response.redirectedUrl == '/series/list'
    }
}
