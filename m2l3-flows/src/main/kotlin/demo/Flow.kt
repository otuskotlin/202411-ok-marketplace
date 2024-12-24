package demo

import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.runBlocking


fun interface Flow<out T> {
    suspend fun collect(collector: FlowCollector<T>)
}

fun interface FlowCollector<in T> {
    suspend fun emit(value: T)
}

class SimpleFlow(private val data: List<Int>): Flow<Int> {
    override suspend fun collect(collector: FlowCollector<Int>) {
        for (item in data) {
            collector.emit(item)
        }
    }
}

class PrintCollector : FlowCollector<Int> {
    override suspend fun emit(value: Int) {
        println(value)
    }
}

fun main() = runBlocking {

    val simpleFlow = SimpleFlow(listOf(1,2,3,4,5))

    val printCollector = PrintCollector()

    simpleFlow.collect(printCollector)

}