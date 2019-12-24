package cmd

import kotlin.system.exitProcess

fun validateArgs(args: Array<String>) {
    if (args.size != 2) {
        printUsage()
        exitProcess(1)
    }
}

fun printUsage() {
    print("""
        USAGE: java -jar stunning-memory-0.0.1-all.jar GITHUB_LOGIN GITHUB_TOKEN
    """.trimIndent())
}