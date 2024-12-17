package demo

class Greeter {
    var greeting: String = "Привет"

    fun greet() {
        println(greeting)
    }
}

fun main() {
    val greeter = Greeter()

    val action: Greeter.() -> Unit = {
        greeting = "Hello"
        greet()
    }

    greeter.greet()
    greeter.action()

}
