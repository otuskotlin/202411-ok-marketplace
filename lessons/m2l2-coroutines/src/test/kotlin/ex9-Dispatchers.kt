package ru.otus.otuskotlin.coroutines

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.test.Test

class Ex10Dispatchers {

    private fun CoroutineScope.createCoro() {
        repeat(30) {
            launch {
                println("coroutine $it, start")
                Thread.sleep(500)
                println("coroutine $it, end")
            }
        }
    }

    @Test
    fun default() = runBlocking {
        createCoro()
    }

    @Test
    fun io() = runBlocking {
        withContext(Dispatchers.IO) { createCoro() }
    }

    @Test
    fun custom() = runBlocking {
//        val dispatcher = Executors.newFixedThreadPool(8).asCoroutineDispatcher()
        @OptIn(DelicateCoroutinesApi::class)
        val dispatcher = newFixedThreadPoolContext(8, "single")


        dispatcher.use {
            withContext(Job() + dispatcher) { createCoro() }
        }
    }

    @Test
    fun unconfined(): Unit = runBlocking(Dispatchers.Default) {
        withContext(Dispatchers.Unconfined) {
            launch() {
                println("start coroutine ${Thread.currentThread().name}")
                suspendCoroutine {
                    println("suspend function, start")
                    thread {
                        println("suspend function, background work")
                        Thread.sleep(1000)
                        it.resume("Data!")
                    }
                }
                println("end coroutine")
            }
        }
    }
}
