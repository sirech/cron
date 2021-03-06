/*
 * This Kotlin source file was generated by the Gradle 'init' task.
 */
package demo

class App {
    val greeting: String
        get() {
            return "Hello World!"
        }
}

fun main(args: Array<String>) {
    if(args.isEmpty()) {
        println("No expression provided")
        return
    }

    val parser = Parser(args[0])
    val cron = parser.parse()
    val output = cron?.print() ?: "Couldn't parse expression"
    println(output)
}
