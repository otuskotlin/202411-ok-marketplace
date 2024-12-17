package demo

data class Engineer(
    var name: String = "",
    var age: Int = 0,
    var country: String = ""
)

fun main() {
    val john = Engineer()
    john.name = "John"
    john.age = 30
    john.country = "USA"

    val bob = Engineer().apply {
        name = "Bob"
        age = 33
        country = "UK"
    }

    println("$john\n$bob")
}