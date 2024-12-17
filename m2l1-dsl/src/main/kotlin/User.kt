import java.time.LocalDateTime

data class User (
    val id: String,

    // NameContext
    val firstName: String,
    val secondName: String,
    val lastName: String,

    // ContactsContext
    val phone: String,
    val email: String,

    // ActionsContext
    val actions: Set<Action>,

    // AvailableContext
    val available: List<LocalDateTime>

)