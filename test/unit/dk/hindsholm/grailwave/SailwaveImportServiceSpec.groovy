package dk.hindsholm.grailwave

import dk.nykredit.grailwave.sailwave.SailwaveImportService
import spock.lang.Specification

@TestFor(SailwaveImportService)
@Mock([Series,Fleet,Boat,Person,Race,Finish])
class SailwaveImportServiceSpec extends Specification {

    def 'import test'() {
        when:
        Series series = service.importSeries(new File("misc/TestCase.xml"))

        then:
        series != null
        series.fleets.size() == 2
        Fleet.count() == 2
        Fleet stor = Fleet.findByName('Stor')
        stor.races.size() == 5
        stor.races.each { r ->
            assert r.finishes.size() == 4
        }
        Fleet lille = Fleet.findByName('Lille')
        lille.races.size() == 5
        lille.races.each { r ->
            assert r.finishes.size() == 8
        }
    }
    
    def 'parse race line'() {
        when:
        def race = service.parseRaceLine('Fleet^Stor^^^^^^^^^^^=^=^=^=^=^=|18:00|Elapsed time|1|5.5nm|Let|0||0|0|A bb|||1')
        
        then:
        race.fleet == 'Stor'
        race.time == '18:00'
        race.length == '5.5nm'
        race.windStrength == 'Let' 
    }

}
