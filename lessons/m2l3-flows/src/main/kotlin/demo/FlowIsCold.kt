package demo

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main(): Unit = runBlocking {

    val flow = flow<Int> {
        println("Started")
        emit(1)
    }

    launch {
        flow.collect()
    }

    launch {
        flow.collect()
    }

}