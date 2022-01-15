package demo

import java.lang.IllegalArgumentException

class Parser(val crontab: String) {
    private fun map(field: String) = when (field) {
        "hour" -> 1
        "command" -> 5
        else -> -1
    }

    private fun field(field: String) = when (field) {
        "hour" -> Field()
        else -> throw IllegalArgumentException("$field doesn't exist")
    }

    private fun process(fields: List<String>, field: String): List<Int> {
        val raw = fields[map(field)]
        return field(field).parse(raw)
    }

    fun parse(): Cron {
        val fields = crontab.split(" ")
        return Cron(
            hour = process(fields, "hour"),
            command = fields[map("command")]
        )
    }
}