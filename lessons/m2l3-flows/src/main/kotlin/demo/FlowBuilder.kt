package demo

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

fun main(): Unit = runBlocking {

    println("пустой поток")
    emptyFlow<Int>().collect{ println(it) }

    println("Поток из набора элементов")
    flowOf(1,2,3).collect{ println(it) }

    println("Ленивый билдер")
    flow {

        emit(1)
        emit(2)
        delay(1000)
        emit(3)
    }.collect{ println(it) }

    println("Поток из коллекции")
    listOf(1,2,3).asFlow().collect{ println(it) }

    println("Поток из последовательности")
    sequenceOf(1,2,3).asFlow().collect{ println(it) }

    println("Контекстно- и потоко-безопасный билдер")
    channelFlow {
        launch { send("foo") }
        delay(100)
        send("bar")
    }.collect{ println(it) }

    println("callbackFlow")
    callbackFlow {
        val timer = Timer()
        timer.scheduleAtFixedRate(delay = 0L, period = 1000L) {
            trySendBlocking("text")
        }
        awaitClose{ timer.cancel() }
    }.take(3).collect {println(it)}



}