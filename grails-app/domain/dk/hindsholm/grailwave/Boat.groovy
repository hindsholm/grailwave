package dk.hindsholm.grailwave

class Boat {
    
    Person skipper
    String type
    String name
    String sailNumber
    Double[] rating = []
    String comments
    
    static belongsTo = [ fleet : Fleet ]
    
    static constraints = {
        skipper blank:false
        type blank:false
        sailNumber blank:false
    }
    
    String getRating() {
        rating.join(',')
    }
    
    void setRating(String s) {
        rating = s.split(',')*.toDouble()
    }
    
    String toString() {
        type + ': ' + name
    }
}
