package demo

import java.util.*

fun String.modify(modification: String.() -> String): String {
    return this.modification()
}

fun main() {
    val result = "Hello".modify {
        this.uppercase(Locale.getDefault())
    }
    print(result)
}