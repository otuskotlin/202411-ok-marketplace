package dsl

import User

fun buildUser(block: UserBuilder.() -> Unit): User
= UserBuilder().apply(block).build()