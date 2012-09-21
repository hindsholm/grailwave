package dk.hindsholm.grailwave

import org.springframework.dao.DataIntegrityViolationException

class FleetController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [fleetInstanceList: Fleet.list(params), fleetInstanceTotal: Fleet.count()]
    }

    def create() {
        [fleetInstance: new Fleet(params)]
    }

    def save() {
        def fleetInstance = new Fleet(params)
        if (!fleetInstance.save(flush: true)) {
            render(view: "create", model: [fleetInstance: fleetInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'fleet.label', default: 'Fleet'), fleetInstance.id])
        redirect(action: "show", id: fleetInstance.id)
    }

    def show(Long id) {
        def fleetInstance = Fleet.get(id)
        if (!fleetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fleet.label', default: 'Fleet'), id])
            redirect(action: "list")
            return
        }

        [fleetInstance: fleetInstance]
    }

    def edit(Long id) {
        def fleetInstance = Fleet.get(id)
        if (!fleetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fleet.label', default: 'Fleet'), id])
            redirect(action: "list")
            return
        }

        [fleetInstance: fleetInstance]
    }

    def update(Long id, Long version) {
        def fleetInstance = Fleet.get(id)
        if (!fleetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fleet.label', default: 'Fleet'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (fleetInstance.version > version) {
                fleetInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'fleet.label', default: 'Fleet')] as Object[],
                          "Another user has updated this Fleet while you were editing")
                render(view: "edit", model: [fleetInstance: fleetInstance])
                return
            }
        }

        fleetInstance.properties = params

        if (!fleetInstance.save(flush: true)) {
            render(view: "edit", model: [fleetInstance: fleetInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'fleet.label', default: 'Fleet'), fleetInstance.id])
        redirect(action: "show", id: fleetInstance.id)
    }

    def delete(Long id) {
        def fleetInstance = Fleet.get(id)
        if (!fleetInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'fleet.label', default: 'Fleet'), id])
            redirect(action: "list")
            return
        }

        try {
            fleetInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'fleet.label', default: 'Fleet'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'fleet.label', default: 'Fleet'), id])
            redirect(action: "show", id: id)
        }
    }
}
