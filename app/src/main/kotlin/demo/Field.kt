package demo

import java.lang.NumberFormatException

class Field(private val range: IntRange) {
    fun parse(expression: String): List<Int>? {
        if (expression == "*") {
            return range.toList()
        }

        val value = expression.asNumber()
        return if (value == null) {
            null
        } else {
            listOf(value)
        }
    }

    fun String.asNumber() = try {
        this.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}