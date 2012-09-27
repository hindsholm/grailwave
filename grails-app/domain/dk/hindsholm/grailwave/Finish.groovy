package dk.hindsholm.grailwave

class Finish {

    enum Code {

        DNC('Did not start; did not come to the starting area'),
        DNS('Did not start (other than DNC and OCS)'),
        OCS('Did not start; on the course side of the starting line and broke rule 29.1 or 30.1'),
        ZFP('20% penalty under rule 30.2'),
        BFD('Disqualification under rule 30.3'),
        SCP('Took a scoring penalty under rule 44.3'),
        DNF('Did not finish'),
        RAF('Retired after finishing'),
        DSQ('Disqualification'),
        DNE('Disqualification not excludable under rule 88.3(b)'),
        RDG('Redress given'),

        String description

        Code(descr) {
            description = descr
        }
    }

    Boat boat
    Code code
    String time

    static belongsTo = [ race : Race ]

    static constraints = {
        code nullable:true
        time nullable:true
    }

    String toString() {
        "${boat.name} " + (time ?: code.toString())
    }
}
