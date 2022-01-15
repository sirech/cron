package demo

class Parser(val crontab: String) {
    private fun map(field: String) = when (field) {
        "command" -> 5
        else -> -1
    }

    fun parse(): Cron {
        val fields = crontab.split(" ")
        return Cron(fields[map("command")])
    }
}