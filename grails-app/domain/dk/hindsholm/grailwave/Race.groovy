package dk.hindsholm.grailwave

class Race {
    
    String name
    Date startTime
    
    static belongsTo = [ series : Series ]
    
    static constraints = {
    }
}
