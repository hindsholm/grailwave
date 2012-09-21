package dk.hindsholm.grailwave

import org.springframework.dao.DataIntegrityViolationException

class RaceController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [raceInstanceList: Race.list(params), raceInstanceTotal: Race.count()]
    }

    def create() {
        [raceInstance: new Race(params)]
    }

    def save() {
        def raceInstance = new Race(params)
        if (!raceInstance.save(flush: true)) {
            render(view: "create", model: [raceInstance: raceInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'race.label', default: 'Race'), raceInstance.id])
        redirect(action: "show", id: raceInstance.id)
    }

    def show(Long id) {
        def raceInstance = Race.get(id)
        if (!raceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'race.label', default: 'Race'), id])
            redirect(action: "list")
            return
        }

        [raceInstance: raceInstance]
    }

    def edit(Long id) {
        def raceInstance = Race.get(id)
        if (!raceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'race.label', default: 'Race'), id])
            redirect(action: "list")
            return
        }

        [raceInstance: raceInstance]
    }

    def update(Long id, Long version) {
        def raceInstance = Race.get(id)
        if (!raceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'race.label', default: 'Race'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (raceInstance.version > version) {
                raceInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'race.label', default: 'Race')] as Object[],
                          "Another user has updated this Race while you were editing")
                render(view: "edit", model: [raceInstance: raceInstance])
                return
            }
        }

        raceInstance.properties = params

        if (!raceInstance.save(flush: true)) {
            render(view: "edit", model: [raceInstance: raceInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'race.label', default: 'Race'), raceInstance.id])
        redirect(action: "show", id: raceInstance.id)
    }

    def delete(Long id) {
        def raceInstance = Race.get(id)
        if (!raceInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'race.label', default: 'Race'), id])
            redirect(action: "list")
            return
        }

        try {
            raceInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'race.label', default: 'Race'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'race.label', default: 'Race'), id])
            redirect(action: "show", id: id)
        }
    }
}
