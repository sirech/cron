package demo

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class ParserTest {
    val crontab = "*/15 0 1,15 * 1-5 /usr/bin/find"
    val parser = Parser(crontab)

    @Test
    fun `parses the command`() {
        expectThat(parser.parse())
            .get { command }
            .isEqualTo("/usr/bin/find")

    }
}