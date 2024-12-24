package demo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext

suspend fun main() {

    withContext(Dispatchers.Default) {
        val result = flow {
            println("Создание числа в потоке: ${Thread.currentThread().name}")
            emit(10)
        }

            .map { number ->
                println("x 2 в потоке: ${Thread.currentThread().name}")
                number * 2
            }
            .flowOn(Dispatchers.IO)
            .filter { nunber ->
                println("> 15 в потоке: ${Thread.currentThread().name}")
                nunber > 15
            }
            .flowOn(Dispatchers.Default)
            .single()

        println("Результат $result получен в потоке: ${Thread.currentThread().name}")

    }

}