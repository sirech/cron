package demo

import java.lang.StringBuilder

/**
 * This class is purely representational.
 *
 * It represents a cron expression as an expanded list of values, and can print itself as a table.
 */
data class Cron(
    val minute: List<Int>,
    val hour: List<Int>,
    val dayOfMonth: List<Int>,
    val month: List<Int>,
    val dayOfWeek: List<Int>,
    val command: String
) {
    fun print(): String {
        return StringBuilder().also { sb ->
            val lines = listOf(
                formatLine("minute", minute.joinToString(" ")),
                formatLine("hour", hour.joinToString(" ")),
                formatLine("day of month", dayOfMonth.joinToString(" ")),
                formatLine("month", month.joinToString(" ")),
                formatLine("day of week", dayOfWeek.joinToString(" ")),
                formatLine("command", command)
            )
            lines.forEach { sb.append(it) }
        }.toString()
    }

    private fun formatLine(header: String, value: String) = String.format("%-13s %s\n", header, value)
}