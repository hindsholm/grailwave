package dk.hindsholm.grailwave

import org.springframework.dao.DataIntegrityViolationException

class SeriesController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [seriesInstanceList: Series.list(params), seriesInstanceTotal: Series.count()]
    }

    def create() {
        [seriesInstance: new Series(params)]
    }

    def save() {
        def seriesInstance = new Series(params)
        if (!seriesInstance.save(flush: true)) {
            render(view: "create", model: [seriesInstance: seriesInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'series.label', default: 'Series'), seriesInstance.id])
        redirect(action: "show", id: seriesInstance.id)
    }

    def show(Long id) {
        def seriesInstance = Series.get(id)
        if (!seriesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'series.label', default: 'Series'), id])
            redirect(action: "list")
            return
        }

        [seriesInstance: seriesInstance]
    }

    def edit(Long id) {
        def seriesInstance = Series.get(id)
        if (!seriesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'series.label', default: 'Series'), id])
            redirect(action: "list")
            return
        }

        [seriesInstance: seriesInstance]
    }

    def update(Long id, Long version) {
        def seriesInstance = Series.get(id)
        if (!seriesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'series.label', default: 'Series'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (seriesInstance.version > version) {
                seriesInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'series.label', default: 'Series')] as Object[],
                          "Another user has updated this Series while you were editing")
                render(view: "edit", model: [seriesInstance: seriesInstance])
                return
            }
        }

        seriesInstance.properties = params

        if (!seriesInstance.save(flush: true)) {
            render(view: "edit", model: [seriesInstance: seriesInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'series.label', default: 'Series'), seriesInstance.id])
        redirect(action: "show", id: seriesInstance.id)
    }

    def delete(Long id) {
        def seriesInstance = Series.get(id)
        if (!seriesInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'series.label', default: 'Series'), id])
            redirect(action: "list")
            return
        }

        try {
            seriesInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'series.label', default: 'Series'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'series.label', default: 'Series'), id])
            redirect(action: "show", id: id)
        }
    }
}
