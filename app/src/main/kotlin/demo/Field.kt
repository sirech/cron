package demo

import java.lang.NumberFormatException

class Field(private val range: IntRange) {
    fun parse(expression: String): List<Int>? {
        if (expression == "*") {
            return range.toList()
        }

        if ('-' in expression) {
            return rangeOfValues(expression)
        }

        if (',' in expression) {
            return multipleValues(expression)
        }

        val value = singleValue(expression)
        return value?.let { listOf(it) }
    }

    private fun rangeOfValues(expression: String): List<Int>? {
        val parts = expression.split("-")
        val start = singleValue(parts[0])
        val end = singleValue(parts[1])

        return if (start == null || end == null) {
            null
        } else {
            (start..end).toList()
        }
    }

    private fun multipleValues(expression: String): List<Int>? {
        val parts = expression.split(",")
        val values = parts.map { singleValue(it) }
        return if (values.any { it == null }) {
            null
        } else {
            values.map { it!! }
        }
    }

    private fun singleValue(expression: String): Int? {
        val value = expression.asNumber()
        return if (value == null || value !in range) {
            null
        } else {
            value
        }
    }

    private fun String.asNumber() = try {
        this.toInt()
    } catch (e: NumberFormatException) {
        null
    }
}