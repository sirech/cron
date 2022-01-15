package demo

import java.lang.IllegalArgumentException

class Parser(private val crontab: String) {
    private fun map(field: String) = when (field) {
        "hour" -> 1
        "command" -> 5
        else -> -1
    }

    private fun field(field: String) = when (field) {
        "hour" -> Field()
        else -> throw IllegalArgumentException("$field doesn't exist")
    }

    private fun process(fields: List<String>, field: String): List<Int>? {
        val raw = fields[map(field)]
        return field(field).parse(raw)
    }

    fun parse(): Cron? {
        val fields = crontab.split(" ")
        val hour = process(fields, "hour")

        if (listOf(hour).any { it == null }) {
            return null
        }

        return Cron(
            hour = hour!!,
            command = fields[map("command")]
        )
    }
}