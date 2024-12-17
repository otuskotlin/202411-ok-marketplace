package demo

@DslMarker
annotation class PersonDsl

@PersonDsl
data class Person (
    var id: Long = 0L,
    var name: String = "",
    var age: Int = 0,

    var contacts: Contacts = Contacts()

)

@PersonDsl
data class Contacts(
    var phone: String = "",
    var email: String = ""
)

@PersonDsl
fun person(blok: Person.() -> Unit): Person {
    return Person().apply(blok)
}

@PersonDsl
fun Person.contacts(block: Contacts.() -> Unit) {
    contacts.apply(block)
}

fun main() {
    val nik = person {
        id = 1L
        name = "Nik"
        age = 99

        contacts {
            phone = "123-45-67"
            email = "nil@mail.com"
        }

    }

    print(nik)
}