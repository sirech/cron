package demo

import java.lang.IllegalArgumentException

class Parser(private val crontab: String) {
    private fun map(field: String) = when (field) {
        "minute" -> 0
        "hour" -> 1
        "dayOfMonth" -> 2
        "month" -> 3
        "dayOfWeek" -> 4
        "command" -> 5
        else -> -1
    }

    private fun field(field: String) = when (field) {
        "minute" -> Field(0..59)
        "hour" -> Field(0..23)
        "dayOfMonth" -> Field(1..31)
        "month" -> Field(1..12)
        "dayOfWeek" -> Field(0..7)
        else -> throw IllegalArgumentException("$field doesn't exist")
    }

    private fun process(fields: List<String>, field: String): List<Int>? {
        val raw = fields[map(field)]
        return field(field).parse(raw)
    }

    fun parse(): Cron? {
        val fields = crontab.split(" ")

        if(fields.size != 6) return null

        val minute = process(fields, "minute")
        val hour = process(fields, "hour")
        val dayOfMonth = process(fields, "dayOfMonth")
        val month = process(fields, "month")
        val dayOfWeek = process(fields, "dayOfWeek")

        if (listOf(minute, hour, dayOfMonth, month, dayOfWeek).any { it == null }) {
            return null
        }

        return Cron(
            minute = minute!!,
            hour = hour!!,
            dayOfMonth = dayOfMonth!!,
            month = month!!,
            dayOfWeek = dayOfWeek!!,
            command = fields[map("command")]
        )
    }
}