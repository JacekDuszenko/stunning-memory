package factory

import java.time.LocalDate
import java.time.ZoneId
import java.util.*
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors
import java.util.stream.Stream

class DateRange {
    companion object {
        private const val dayInMs: Long = 1000 * 60 * 60 * 24

        fun getAllDatesFromYearAgo(): List<Date> {
            val yearAgo = getYearAgoDate()
            return getAllDatesUntilNow(yearAgo)
        }

        private fun getYearAgoDate() = Date(System.currentTimeMillis() - 365 * 10 * dayInMs)

        fun getAllDatesUntilNow(from: Date): List<Date> {
            val now = Date()
            val daysBetween = calculateDaysBetweenTwoDates(now, from)
            if (daysBetween < 0) {
                throw Exception("given from date is greater than now")
            }
            val local: LocalDate = from.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
            return Stream.iterate(local, { date -> date.plusDays(1) })
                .limit(daysBetween)
                .map { convertLocalDateToDate(it) }
                .collect(Collectors.toList())
        }

        private fun convertLocalDateToDate(it: LocalDate) =
            Date.from(it.atStartOfDay(ZoneId.systemDefault()).toInstant())

        private fun calculateDaysBetweenTwoDates(now: Date, from: Date) =
            TimeUnit.DAYS.convert(now.time - from.time, TimeUnit.MILLISECONDS)
    }
}