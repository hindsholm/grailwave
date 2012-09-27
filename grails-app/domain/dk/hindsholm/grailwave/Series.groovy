package dk.hindsholm.grailwave

class Series {
    
    String name
    String organizer
    
    static hasMany = [ fleets : Fleet ]

    static constraints = {
        name blank:false
        organizer blank:false
    }
    
    String toString() {
        name
    }
    
}
