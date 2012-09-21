package dk.hindsholm.grailwave

import org.springframework.dao.DataIntegrityViolationException

class BoatController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [boatInstanceList: Boat.list(params), boatInstanceTotal: Boat.count()]
    }

    def create() {
        [boatInstance: new Boat(params)]
    }

    def save() {
        def boatInstance = new Boat(params)
        if (!boatInstance.save(flush: true)) {
            render(view: "create", model: [boatInstance: boatInstance])
            return
        }

		flash.message = message(code: 'default.created.message', args: [message(code: 'boat.label', default: 'Boat'), boatInstance.id])
        redirect(action: "show", id: boatInstance.id)
    }

    def show() {
        def boatInstance = Boat.get(params.id)
        if (!boatInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'boat.label', default: 'Boat'), params.id])
            redirect(action: "list")
            return
        }

        [boatInstance: boatInstance]
    }

    def edit() {
        def boatInstance = Boat.get(params.id)
        if (!boatInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'boat.label', default: 'Boat'), params.id])
            redirect(action: "list")
            return
        }

        [boatInstance: boatInstance]
    }

    def update() {
        def boatInstance = Boat.get(params.id)
        if (!boatInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'boat.label', default: 'Boat'), params.id])
            redirect(action: "list")
            return
        }

        if (params.version) {
            def version = params.version.toLong()
            if (boatInstance.version > version) {
                boatInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'boat.label', default: 'Boat')] as Object[],
                          "Another user has updated this Boat while you were editing")
                render(view: "edit", model: [boatInstance: boatInstance])
                return
            }
        }

        boatInstance.properties = params

        if (!boatInstance.save(flush: true)) {
            render(view: "edit", model: [boatInstance: boatInstance])
            return
        }

		flash.message = message(code: 'default.updated.message', args: [message(code: 'boat.label', default: 'Boat'), boatInstance.id])
        redirect(action: "show", id: boatInstance.id)
    }

    def delete() {
        def boatInstance = Boat.get(params.id)
        if (!boatInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'boat.label', default: 'Boat'), params.id])
            redirect(action: "list")
            return
        }

        try {
            boatInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'boat.label', default: 'Boat'), params.id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'boat.label', default: 'Boat'), params.id])
            redirect(action: "show", id: params.id)
        }
    }
}
