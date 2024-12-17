package dsl

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.TemporalAdjusters

@UserDsl
class AvailableContext {

    private val _availabilities: MutableList<LocalDateTime> = mutableListOf()

    val availabilities: List<LocalDateTime>
        get() = _availabilities

    fun add(dateTime: LocalDateTime) {
        _availabilities.add(dateTime)
    }

    fun dayTimeOfWeek(day: DayOfWeek, time: String) {
        val dDay = LocalDate.now().with(TemporalAdjusters.next(day))
        val dTime = LocalTime.parse(time)
        add(LocalDateTime.of(dDay, dTime))
    }

}