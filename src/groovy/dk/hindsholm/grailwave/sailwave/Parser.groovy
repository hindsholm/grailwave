package dk.hindsholm.grailwave.sailwave

import dk.hindsholm.grailwave.Boat
import dk.hindsholm.grailwave.Fleet
import dk.hindsholm.grailwave.Person
import dk.hindsholm.grailwave.Series

class Parser {

    static parse(File f) {
        def data = new XmlSlurper().parse(f)
        Series series = new Series(name:data.globals.serevent.text(), organizer:data.globals.servenue.text())
        println "Series ${series}"
        def fleets = [:]
        data.competitors.competitor.each { comp ->
            def fleetId = comp.compfleet.text()
            def fleet = fleets[fleetId]
            if (!fleet) {
                // fleet = series.newFleet(fleetId)
                fleet = new Fleet(name:fleetId)
                fleets[fleetId] = fleet
            }
            println("Fleet ${fleet}")
            def helm = new Person(name:comp.comphelmname.text(),
                    phone:comp.comphelmphone.text(),
                    email:comp.comphelmemail.text())
            println "Person ${helm}"
        }
    }

    static void main(String[] args) {
        Parser.parse(new File("misc/Onsdagssejladser2010.xml"))
    }
}


