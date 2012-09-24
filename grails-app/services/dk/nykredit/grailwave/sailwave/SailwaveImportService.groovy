package dk.nykredit.grailwave.sailwave

import dk.hindsholm.grailwave.*

class SailwaveImportService {

    Series importSeries(File f) {
        def data = new XmlSlurper().parse(f)
        Series series = new Series(name:data.globals.serevent.text(), organizer:data.globals.servenue.text()).save()
        def boats = [:]
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
            boats[comp.@handle.text()] = boat
        }
        def races = [:]
        data.races.race.each { r ->
            Race race = new Race(name:r.racename.text(), date:r.racedate.text())
            series.addToRaces(race)
            race.save()
            races[r.@handle.text()] = race
        }
        data.results.result.each { result ->
            Boat boat = boats[result.@comHandle.text()]
            Race race = races[result.@racHandle.text()]
            Finish finish = new Finish(boat:boat)
            if (result.rele.text()) {
                finish.time = result.rele.text()
            } else {
                finish.code = Finish.Code.valueOf(result.rcod.text())
            }
            race.addToFinishes(finish)
            finish.save()
        }
        return series
    }

}
