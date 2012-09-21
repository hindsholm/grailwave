package dk.hindsholm.grailwave

class Fleet {
    
    String name
    
    static belongsTo = [ series : Series ]
    static hasMany = [ boats : Boat ]
    
    static constraints = {
        name blank:false
    }
    
    String toString() {
        name
    }
}
