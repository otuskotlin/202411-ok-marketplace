package ru.otus.otuskotlin.oop

typealias StringAlias = String

@JvmInline
value class StringInline(val s: String)

fun main() {
    val nameAlias: StringAlias = "name"
    val name: String = nameAlias

    val nameInline: StringInline = StringInline("name")
    // val name: String = nameInline - ошибка
}
