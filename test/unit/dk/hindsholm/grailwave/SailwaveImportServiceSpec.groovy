package dk.hindsholm.grailwave

import dk.nykredit.grailwave.sailwave.SailwaveImportService
import spock.lang.Specification

@TestFor(SailwaveImportService)
@Mock([Series,Fleet,Boat,Person])
class SailwaveImportServiceSpec extends Specification {

    def "import test"() {
        when:
        Series series = service.parse(new File("misc/Onsdagssejladser2010.xml"))

        then:
        series != null
        series.fleets.size() == 2
    }

}
