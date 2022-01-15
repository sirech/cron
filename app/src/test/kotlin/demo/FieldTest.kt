package demo

import strikt.api.expectThat
import strikt.assertions.get
import strikt.assertions.hasSize
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class FieldTest {
    val field = Field()

    @Test
    fun `parses an individual value`() {
        val result = field.parse("0")
        expectThat(result).hasSize(1)
        expectThat(result)[0].isEqualTo(0)
    }
}