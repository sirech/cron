package demo

import java.lang.NumberFormatException

class Field(private val range: IntRange) {
    fun parse(expression: String): List<Int>? {
        if (expression == "*") {
            return range.toList()
        }

        if (',' in expression) {
            return multipleValues(expression)
        }

        val value = expression.asNumber()
        return if (value == null || value !in range) {
            null
        } else {
            listOf(value)
        }
    }

    private fun multipleValues(expression: String): List<Int>? {
        val parts = expression.split(",")
        val values = parts.map { it.asNumber() }

        return if (values.any { it == null || it !in range }) {
            null
        } else {
            values.map { it!! }
        }
    }

    fun String.asNumber() = try {
        this.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}