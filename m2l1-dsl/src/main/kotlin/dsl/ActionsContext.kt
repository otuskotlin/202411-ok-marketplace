package dsl

import Action

@UserDsl
class ActionsContext {

    private val _actions: MutableSet<Action> = mutableSetOf()

    fun build(): Set<Action> = _actions.toSet()

    fun add(action: Action) = _actions.add(action)

    fun add(value: String) = add(Action.valueOf(value))

    operator fun Action.unaryPlus() = add(this)

    operator fun String.unaryPlus() = add(this)
}