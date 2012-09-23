package dk.nykredit.grailwave.sailwave

import dk.hindsholm.grailwave.Boat
import dk.hindsholm.grailwave.Fleet
import dk.hindsholm.grailwave.Person
import dk.hindsholm.grailwave.Series


class SailwaveImportService {

    Series importSeries(File f) {
        def data = new XmlSlurper().parse(f)
        Series series = new Series(name:data.globals.serevent.text(), organizer:data.globals.servenue.text()).save()
        data.competitors.competitor.each { comp ->
            def fleetName = comp.compfleet.text()
            Fleet fleet = Fleet.findBySeriesAndName(series, fleetName)
            if (!fleet) {
                fleet = new Fleet(name:fleetName)
                series.addToFleets(fleet)
                fleet.save()
            }
            Person skipper = new Person(name:comp.comphelmname.text(),
                    phone:comp.comphelmphone.text(),
                    email:comp.comphelmemail.text()).save()
            Boat boat = new Boat(skipper:skipper, 
                type:comp.compclass.text(),
                name:comp.compboat.text(),
                sailNumber:comp.compsailno.text(),
                rating:comp.compwindrats.text(),
                comments:comp.compprivatenotes.text())
            fleet.addToBoats(boat)
            boat.save()
        }
        return series
    }

}
