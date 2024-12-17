package dsl

import Action
import User
import java.time.LocalDateTime
import java.util.*

@UserDsl
class UserBuilder {

    private var id = UUID.randomUUID().toString()

    // NameContext
    private var firstName = ""
    private var secondName = ""
    private var lastName = ""

    // ContactsContext
    private var phone = ""
    private var email = ""

    // ActionsContext
    private var actions = emptySet<Action>()

    // AvailableContext
    private var available = emptyList<LocalDateTime>()

    // Name
    fun name(block: NameContext.() -> Unit) {
        val ctx = NameContext().apply(block)
        firstName = ctx.first
        secondName = ctx.second
        lastName = ctx.last
    }

    // Contacts
    fun contacts(block: ContactsContext.() -> Unit) {
        val ctx = ContactsContext().apply(block)
        phone = ctx.phone
        email = ctx.email
    }

    // Actions
    fun actions(block: ActionsContext.() -> Unit) {
        val ctx = ActionsContext().apply(block)
        actions = ctx.build()
    }

    // Available
    fun availability(block: AvailableContext.() -> Unit) {
        val ctx = AvailableContext().apply(block)
        available = ctx.availabilities
    }

    fun build() = User(
        id = id,
        firstName = firstName,
        secondName = secondName,
        lastName = lastName,
        phone = phone,
        email = email,
        actions = actions,
        available = available
    )

}