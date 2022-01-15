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
    fun `returns null if the expression can not be parsed`() {
        expectThat(Parser("*/15 a 1,15 * 1-5 /usr/bin/find").parse())
            .isNull()
    }

    @Test
    fun `parses the command`() {
        expectThat(parser.parse())
            .isNotNull()
            .get { command }
            .isEqualTo("/usr/bin/find")

    }
}