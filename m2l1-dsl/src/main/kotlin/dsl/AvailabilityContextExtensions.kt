package dsl

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun AvailableContext.sun(time: String) = dayTimeOfWeek(DayOfWeek.SUNDAY, time)
fun AvailableContext.mon(time: String) = dayTimeOfWeek(DayOfWeek.MONDAY, time)
fun AvailableContext.tue(time: String) = dayTimeOfWeek(DayOfWeek.TUESDAY, time)
fun AvailableContext.wed(time: String) = dayTimeOfWeek(DayOfWeek.WEDNESDAY, time)
fun AvailableContext.thu(time: String) = dayTimeOfWeek(DayOfWeek.THURSDAY, time)
fun AvailableContext.fri(time: String) = dayTimeOfWeek(DayOfWeek.FRIDAY, time)
fun AvailableContext.sat(time: String) = dayTimeOfWeek(DayOfWeek.SATURDAY, time)

fun AvailableContext.tomorrow(time: String) {
    val dDay = LocalDate.now().plusDays(1)
    val dTime = LocalTime.parse(time)
    add(LocalDateTime.of(dDay, dTime))
}