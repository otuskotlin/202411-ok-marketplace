package demo

import kotlin.random.Random

fun main() {

    val emptySeq = emptySequence<Int>()
    println("Пустая последовательность: ${emptySeq.toList()}")

    val seqFromElements = sequenceOf(1,2,3)
    println("Последовательность из элементов: ${seqFromElements.toList()}")

    val lazySeq = sequence {
        yield(1)
        Thread.sleep(1000)
        yield(2)
        yield(3)
    }
    println("Ленивая последовательность:")
    lazySeq.forEach { println(it) }

    val listToSeq = listOf(1,2,3).asSequence()
    println("Последовательность из списка: ${listToSeq.toList()}")

    val iterableSeq = Sequence { listOf(1,2,3).iterator() }
    println("Последовательность итераторов: ${iterableSeq.toList()}")

    val randomSeq = generateSequence { Random.nextInt() }
    println("Случайная последовательность")
    randomSeq.take(5).forEach { println(it) }

    val incrementSeq = generateSequence(1) { it + 1 }
    println("Инкрементная последовательность от 1:")
    incrementSeq.take(5).forEach { println(it) }

    val randomIncrementSeq = generateSequence (
        { Random.nextInt() },
        { it + 1 }
    )
    println("Случайная инкрементная последовательность 2:")
    randomIncrementSeq.take(5).forEach { println(it) }
}