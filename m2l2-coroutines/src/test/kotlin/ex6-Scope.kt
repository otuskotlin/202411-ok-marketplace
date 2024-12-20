package ru.otus.otuskotlin.coroutines

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.test.Test

class Ex6Scope {

    @Test
    fun create1() {
        val scope = CoroutineScope(Dispatchers.Main)

        println("scope: $scope")
    }

    @Test
    fun create2() {
        val scope = CoroutineScope(Dispatchers.Main + Job() + CoroutineName("create2"))

        println("scope: $scope")
    }

    @Test
    fun create3() {
        val scope = CoroutineScope(Dispatchers.Main + SomeData(10, 20))
        val data = scope.coroutineContext[SomeData]

        println("scope: $scope, $data")
    }

    private fun CoroutineScope.scopeToString(): String =
        "Job = ${coroutineContext[Job]}, Dispatcher = ${coroutineContext[ContinuationInterceptor]}, Data = ${coroutineContext[SomeData]}"

    @Test
    fun defaults() {
        val scope = CoroutineScope(SomeData(10, 20))
        scope.launch {
            println("Child1: ${scopeToString()}")
        }
        scope.launch(SomeData(1, 2)) {
            println("Child2: ${scopeToString()}")
        }

        println("This: ${scope.scopeToString()}")
    }

    data class SomeData(val x: Int, val y: Int) : AbstractCoroutineContextElement(SomeData) {
        companion object : CoroutineContext.Key<SomeData>
    }

    @Test
    fun ioScope() {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            val userName = async {
                selectUserNameFromDB()
            }
            val taskStatus = async {
                selectTaskStatusFromDB()
            }

            println("scope: $scope")
            println("User name: ${userName.await()}, Task status: ${taskStatus.await()}")
        }

        Thread.sleep(200) // wait for launch completion
    }

    private suspend fun selectUserNameFromDB(): String {
        delay(50)
        return "FirstName"
    }

    private suspend fun selectTaskStatusFromDB(): String {
        delay(50)
        return "Done"
    }
}
