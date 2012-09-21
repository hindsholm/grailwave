package dk.hindsholm.grailwave

class Boat {
    
    Person skipper
    String type
    String name
    String sailNumber
    double rating
    String comments
    
    static belongsTo = [ fleet : Fleet ]
    
    static constraints = {
        skipper blank:false
        type blank:false
        sailNumber blank:false
    }
    
    String toString() {
        type + ': ' + name
    }
}
