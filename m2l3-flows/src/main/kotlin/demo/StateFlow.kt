package demo

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val mshState = MutableStateFlow("State 1")

    val job = launch {

        mshState.collect { state ->
            println("Collected state: $state")
        }

    }

    mshState.emit("State 2")
    delay(500)

    mshState.emit("State 3")
    delay(500)

    println("Final state: $mshState")

    job.cancel()
}