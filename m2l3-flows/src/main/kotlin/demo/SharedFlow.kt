package demo

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val shFlow = MutableSharedFlow<String>()

    val j1 = launch {
        shFlow.collect{ println("XX получено: $it") }
    }

    val j2 = launch {
        shFlow.collect{ println("YY получено: $it") }
    }

    launch {
        repeat(3) {
            shFlow.emit("Message $it")
            delay(500)
        }
    }

    delay(2000)

    j1.cancel()
    j2.cancel()

}