import dsl.buildUser
import dsl.mon
import dsl.tomorrow


fun main() {

    // Итоговая последовательность разработки DSL
    // 1) создаем аннотацию @UserDsl
    // 2) создаем вспомогательные классы NameContext, ContactsContext, ActionsContext, AvailableContext
    // 3) добавляем расширение для AvailabilityContextExtensions
    // 4) создать основной класс UserBuilder
    // 5) добавить функцию buildUser
    // 6) пример использования DSL

    val user = buildUser {
        name {
            first = "Bob"
            second = "M"
            last = "Doe"
        }

        contacts {
            phone = "1234"
            email = "bob@email.com"
        }

        actions {
            +Action.CREATE
            +"UPDATE"
        }

        availability {
            mon("10:00")
            tomorrow("14:00")
        }
    }

    println(user)

}