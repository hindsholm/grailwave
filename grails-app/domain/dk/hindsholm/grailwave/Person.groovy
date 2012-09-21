package dk.hindsholm.grailwave

class Person {
    
    String name
    String phone
    String email
    
    static constraints = {
        name blank:false
        phone nullable:true
        email email:true, nullable:true
    }
    
    String toString() {
        name
    }
}
