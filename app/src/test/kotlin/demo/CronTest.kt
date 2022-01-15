package demo

import strikt.api.expectThat
import strikt.assertions.isEqualTo
import kotlin.test.Test

internal class CronTest {
    @Test
    fun `formats output correctly`() {
        expectThat(Cron().print()).isEqualTo("")
    }
}
