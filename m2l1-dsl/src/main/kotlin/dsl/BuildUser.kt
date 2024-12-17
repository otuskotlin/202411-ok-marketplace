package ru.otus.otuskotlin.m2l1.dsl

fun buildUser(block: UserBuilder.() -> Unit) = UserBuilder().apply(block).build()