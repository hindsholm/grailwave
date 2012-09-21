package dk.hindsholm.grailwave

import spock.lang.Specification
import grails.test.mixin.domain.DomainClassUnitTestMixin
import grails.test.mixin.TestMixin

@TestMixin(DomainClassUnitTestMixin)
class SailwaveImportSpec extends Specification {

    def "domain mocking"() {
        setup:
        mockDomain(Person)

        when:
        new Person(name: name).save()

        then:
        Person.findByName(name) != null

        where:
        name = "bill"
    }

}
