package demo

import java.lang.NumberFormatException

class Field {
    fun parse(expression: String): List<Int>? {
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