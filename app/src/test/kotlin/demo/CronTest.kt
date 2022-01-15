package demo

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class CronTest {
    val cron = Cron(
        hour = listOf(0),
        month = (1..12).toList(),
        command = "/usr/bin/find"
    )

    @Test
    fun `formats output correctly`() {
        val fixture = "output1".asStream().readTextAndClose()
        val output = cron.print()
        expectThat(output).isEqualTo(fixture)
    }
}
