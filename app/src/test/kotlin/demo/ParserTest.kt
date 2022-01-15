package demo

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import strikt.assertions.isNotNull
import strikt.assertions.isNull
import kotlin.test.Test

internal class ParserTest {
    val crontab = "*/15 0 1,15 * 1-5 /usr/bin/find"
    val parser = Parser(crontab)

    @Test
    fun `returns null if the expression is too short`() {
        expectThat(Parser("*/15 0 1,15 * /usr/bin/find").parse())
            .isNull()
    }

    @Test
    fun `returns null if the expression is too long`() {
        expectThat(Parser("*/15 0 1,15 * 1-5 * /usr/bin/find").parse())
            .isNull()
    }

    @Test
    fun `returns null if the expression can not be parsed`() {
        expectThat(Parser("*/15 a 1,15 * 1-5 /usr/bin/find").parse())
            .isNull()
    }

    @Test
    fun `returns null if the expression runs ouf of range`() {
        expectThat(Parser("*/15 0 1,15,32 * 1-5 /usr/bin/find").parse())
            .isNull()
    }

    @Test
    fun `parses a simple command`() {
        expectThat(Parser("15 0 1 3 5 /usr/bin/find").parse())
            .isNotNull().and {
                get { minute }.isEqualTo(listOf(15))
                get { hour }.isEqualTo(listOf(0))
                get { dayOfMonth }.isEqualTo(listOf(1))
                get { month }.isEqualTo(listOf(3))
                get { dayOfWeek }.isEqualTo(listOf(5))
                get { command }.isEqualTo("/usr/bin/find")
            }

    }

    @Test
    fun `parses the command`() {
        expectThat(parser.parse())
            .isNotNull().and {
                get { minute }.isEqualTo(listOf(0, 15, 30, 45))
                get { hour }.isEqualTo(listOf(0))
                get { dayOfMonth }.isEqualTo(listOf(1, 15))
                get { month }.isEqualTo((1..12).toList())
                get { dayOfWeek }.isEqualTo((1..5).toList())
                get { command }.isEqualTo("/usr/bin/find")
            }

    }

    @Test
    fun `parses the a full wildcard`() {
        expectThat(Parser("* * * * * /usr/bin/find").parse())
            .isNotNull().and {
                get { minute }.isEqualTo((0..59).toList())
                get { hour }.isEqualTo((0..23).toList())
                get { dayOfMonth }.isEqualTo((1..31).toList())
                get { month }.isEqualTo((1..12).toList())
                get { dayOfWeek }.isEqualTo((0..7).toList())
                get { command }.isEqualTo("/usr/bin/find")
            }

    }
}