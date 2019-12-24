package ui

import kotlinx.coroutines.*
import java.util.concurrent.atomic.AtomicInteger

class Histogram(val histogram: MutableMap<String, Long>) {

    fun init(delayInMillis: Long) {
        runBlocking {
            refreshWithDelay(delayInMillis)
        }
    }

    private fun CoroutineScope.refreshWithDelay(delayInMillis: Long): Job {
        return launch {
            val ctr: AtomicInteger = AtomicInteger(0)
            while (true) {
                clearDisplay()
                printHistogram()
                histogram["XD"] = ctr.toLong()
                ctr.incrementAndGet()
                delay(delayInMillis)
            }
        }
    }

    private fun clearDisplay() {
        print("\u001b[H\u001b[2J")
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