package demo

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class CronTest {
    val cron = Cron("/usr/bin/find")

    @Test
    fun `formats output correctly`() {
        val fixture = "output1".asStream().readTextAndClose()
        val output = cron.print()
        expectThat(output).isEqualTo(fixture)
    }
}
