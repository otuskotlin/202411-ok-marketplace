package demo

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() = runBlocking {

    val flow = flow {
        emit(1)
        emit(2)
        emit(3)
    }

    println("--- Пример collect ---")

    flow.collect { println(it) }

    println("\n--- Пример повторного collect ---")

    flow.collect()

    println("\n--- Пример first ---")

    val firstItem = flow.first()
    println("First item: $firstItem")

    println("\n--- Пример first с условием ---")

    val firstMatchingItem = flow.first { it > 1 }
    println("First matching item: $firstMatchingItem")

    println("\n--- Пример single ---")

    val singleFlow = flowOf(42)
    val singleItem = singleFlow.single()
    println("Single item: $singleItem")

    println("\n--- Пример launchIn ---")

    val scope = CoroutineScope(Dispatchers.Default)
    flow.onEach { println("LaunchIn item: $it") }
        .launchIn(scope)

    delay(100)

    println("\n--- Пример toList ---")

    val itemsList = flow.toList()
    println("Items as list: $itemsList")

    scope.cancel()
}