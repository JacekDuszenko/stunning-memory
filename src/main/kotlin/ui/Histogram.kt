package ui

import kotlinx.coroutines.*

class Histogram(private val histogram: MutableMap<String, Long>) {
    companion object {
        const val clearConsoleEscapeSequence: String = "\u001b[H\u001b[2J"
    }

    fun init(delayInMillis: Long) {
        runBlocking {
            refreshWithDelay(delayInMillis)
        }
    }

    private fun CoroutineScope.refreshWithDelay(delayInMillis: Long): Job {
        return launch {
            while (true) {
                clearDisplay()
                printHistogram()
                delay(delayInMillis)
            }
        }
    }

    private fun clearDisplay() {
        print(clearConsoleEscapeSequence)
    }

    private fun printHistogram() {
        val sortedByPopularity = getSortedListFromHistogram()
        for (entry in sortedByPopularity) {
            println("classname: ${entry.key}, occurrences: ${entry.value}")
        }
    }

    private fun getSortedListFromHistogram(): List<Map.Entry<String, Long>> {
        return histogram.entries.toList().sortedByDescending { entry -> entry.value }
    }
}