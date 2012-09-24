package dk.hindsholm.grailwave

import dk.nykredit.grailwave.sailwave.SailwaveImportService
import spock.lang.Specification

@TestFor(SailwaveImportService)
@Mock([Series,Fleet,Boat,Person,Race,Finish])
class SailwaveImportServiceSpec extends Specification {

    def "import test"() {
        when:
        Series series = service.importSeries(new File("misc/Onsdagssejladser2012.xml"))

        then:
        series != null
        series.fleets.size() == 2
        Fleet.count() == 2
    }

}
