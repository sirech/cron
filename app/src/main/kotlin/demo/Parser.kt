package demo

import java.lang.IllegalArgumentException

class Parser(private val crontab: String) {
    private fun map(field: String) = when (field) {
        "hour" -> 1
        "month" -> 3
        "command" -> 5
        else -> -1
    }

    private fun field(field: String) = when (field) {
        "hour" -> Field(0..23)
        "month" -> Field(1..12)
        else -> throw IllegalArgumentException("$field doesn't exist")
    }

    private fun process(fields: List<String>, field: String): List<Int>? {
        val raw = fields[map(field)]
        return field(field).parse(raw)
    }

    fun parse(): Cron? {
        val fields = crontab.split(" ")
        val hour = process(fields, "hour")
        val month = process(fields, "month")

        if (listOf(hour, month).any { it == null }) {
            return null
        }

        return Cron(
            hour = hour!!,
            month = month!!,
            command = fields[map("command")]
        )
    }
}