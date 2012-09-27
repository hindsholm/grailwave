package dk.hindsholm.grailwave

class Race {
    
    String name
    String date
    String length
    String windStrength
    
    static belongsTo = [ fleet : Fleet ]
    static hasMany = [ finishes : Finish ]
    
    static constraints = {
    }
    
    String toString() {
        "${name} - ${date}"
    }
}
