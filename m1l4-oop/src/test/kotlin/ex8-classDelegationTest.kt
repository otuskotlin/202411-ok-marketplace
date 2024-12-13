package ru.otus.otuskotlin.oop

import org.junit.Test
import kotlin.test.assertEquals

internal class ClassDelegationTest {

    interface IDelegate {
        fun x(): String
        fun y(): String
    }

    class MyClass : IDelegate {

        override fun x(): String {
            println("MyClass.x()")
            return "x"
        }

        override fun y(): String = "y"
            .also { println("MyClass.y()") }
    }

    class MyDelegate(
        private val del: IDelegate
    ) : IDelegate by del {

        override fun x(): String {
            println("Calling x()")
            val str = del.x()
            println("Calling x() done")
            return "delegate for ($str)"
        }
    }

    @Test
    fun delegate() {
        val base = MyClass()
        val delegate = MyDelegate(base)

        println("Calling base")
        assertEquals("x", base.x())
        assertEquals("y", base.y())

        println()

        println("Calling delegate")
        assertEquals("delegate for (x)", delegate.x())
        assertEquals("y", delegate.y())
    }
}
