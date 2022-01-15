package demo

import strikt.api.expectThat
import strikt.assertions.*
import kotlin.test.Test

internal class FieldTest {
    val field = Field()

    @Test
    fun `returns null if the field is invalid`() {
        expectThat(field.parse("a"))
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
}