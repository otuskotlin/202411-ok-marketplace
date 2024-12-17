package demo

fun calculate(operation: (Int, Int) -> Int, a: Int, b: Int): Int {
    return operation(a, b)
}

fun main() {
    val result = calculate({x, y -> x + y}, 5, 5)
    print(result)
}