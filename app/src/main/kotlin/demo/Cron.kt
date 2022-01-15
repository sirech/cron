package demo

import java.lang.StringBuilder

class Cron {
    fun minute() = listOf(0, 15, 30, 45)
    fun hour() = listOf(0)
    fun dayOfMonth() = listOf(1, 15)
    fun month() = (1..12).toList()
    fun dayOfWeek() = (1..5).toList()
    fun command() = "/usr/bin/find"

    fun print(): String {
        return StringBuilder().also { sb ->
            val lines = listOf(
                formatLine("minute", minute().joinToString(" ")),
                formatLine("hour", hour().joinToString(" ")),
                formatLine("day of month", dayOfMonth().joinToString(" ")),
                formatLine("month", month().joinToString(" ")),
                formatLine("day of week", dayOfWeek().joinToString(" ")),
                formatLine("command", command())
            )
            lines.forEach { sb.append(it) }
        }.toString()
    }

    private fun formatLine(header: String, value: String) = String.format("%-13s %s\n", header, value)
}