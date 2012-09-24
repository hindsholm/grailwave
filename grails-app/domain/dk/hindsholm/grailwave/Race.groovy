package dk.hindsholm.grailwave

class Race {
    
    String name
    String date
    
    static belongsTo = [ series : Series ]
    static hasMany = [ finishes : Finish ]
    
    static constraints = {
    }
    
    String toString() {
        "${name} - ${date}"
    }
}
