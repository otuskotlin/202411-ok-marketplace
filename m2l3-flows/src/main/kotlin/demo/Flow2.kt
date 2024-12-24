package demo

import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    // Билдер
    flow<Int> {
        emit(1)
        emit(2)
        emit(3)
        emit(4)
        emit(5)
    }
        // Операции преобразования
        .onEach { println("Emitted: $it") }
        .map{ it + 1 }
        .filter { it % 2 == 0 }

        // Терминальная операция
        .collect{
            println("Collected: $it")
        }

}