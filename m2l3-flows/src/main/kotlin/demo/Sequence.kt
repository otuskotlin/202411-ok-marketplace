package demo

class MySequence : Sequence<Int> {

    private val values = listOf(1,2,3,4,5)

    override fun iterator(): Iterator<Int> {
        return values.iterator()
    }
}

fun main() {

    val sequence = MySequence()

    for (value in sequence) {
        println(value)
    }

}