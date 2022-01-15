package demo

import strikt.api.expectThat
import strikt.assertions.*
import kotlin.test.Test

internal class FieldTest {
    val range = 0..23
    val field = Field(range)

    @Test
    fun `returns null if the field is invalid`() {
        expectThat(field.parse("a"))
            .isNull()
    }

    @Test
    fun `returns null if the field is out of range`() {
        expectThat(field.parse("24"))
            .isNull()
    }

    @Test
    fun `parses an individual value`() {
        val result = field.parse("0")
        expectThat(result)
            .isNotNull()
            .hasSize(1)
        expectThat(result)
            .isNotNull()[0].isEqualTo(0)
    }

    @Test
    fun `parses wildcard value`() {
        expectThat(field.parse("*"))
            .isNotNull()
            .isEqualTo(range.toList())
    }

    @Test
    fun `parses a union of values`() {
        expectThat(field.parse("1,15"))
            .isNotNull()
            .isEqualTo(listOf(1, 15))
    }
}