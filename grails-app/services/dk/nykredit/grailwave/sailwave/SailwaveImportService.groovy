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
            def name = r.racename.text()
            def date = r.racedate.text()
            r.starts.racestart.each { rs ->
                // TODO
                def fields = parseRaceLine(rs.text())
                Race race = new Race(name:name, date:date, length:fields.length, windStrength:fields.windStrength)
                Fleet fleet = Fleet.findByName(fields.fleet)
                fleet.addToRaces(race)
                race.save()
                races[r.@handle.text() + fleet.name] = race
            }
        }
        data.results.result.each { result ->
            Boat boat = boats[result.@comHandle.text()]
            Race race = races[result.@racHandle.text() + boat.fleet.name]
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
    
    def parseRaceLine(String line) {
        // Fleet^Stor^^^^^^^^^^^=^=^=^=^=^=|18:00|Elapsed time|1|5.5nm|Let|0||0|0|A bb|||1
        def fields = line.tokenize('|')
        def matcher = (fields[0] =~ /.*Fleet\^([^\^]+)\^/)
        def fleet = matcher[0][1]
        [fleet:fleet, time:fields[1], length:fields[4], windStrength:fields[5]]
    }

}
