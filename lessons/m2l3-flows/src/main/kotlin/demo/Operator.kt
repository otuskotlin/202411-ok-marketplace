package demo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.slf4j.LoggerFactory

fun main() = runBlocking {

    val log = LoggerFactory.getLogger("FlowExample")

    val flow = flow {

        emit(1)
        emit(2)
        emit(3)
        emit(4)
        emit(5)
    }

    flow
        .onEach { log.info("Got Item: $it") }
        .onStart { log.info("Поток стартовал") }
        .onCompletion { log.info("Поток завершен") }

    println("Пример трансформации")

    flow
        .map { it * 2 }
        .filter { it != 4 }
        .take(3)
        .drop(1)
        .collect { println("Преобразован: $it") }

    flow
        .buffer(128, BufferOverflow.DROP_OLDEST)
        .onEach { delay(100) }
        .collect { println("Буфф. элем: $it") }

    // Пример throttling
    flow
        .debounce(200) // Устанавливаем паузу между элементами
        .collect { println("Debounced item: $it") }

    println("\n--- Пример retry ---")

    // Пример retry
    flow {
        emit(1)
        throw RuntimeException("Test Exception")
    }
        .retry(3) { throwable ->
            println("Retry on error: ${throwable.message}")
            true // Повторяем поток на каждой ошибке
        }
        .catch { println("Caught error: ${it.message}") }
        .collect()

    println("\n--- Пример смены контекста ---")

    // Пример смены контекста
    flow
        .map { it * 2 }
        .flowOn(Dispatchers.IO)
        .collect { println("Processed on IO: $it") }
}