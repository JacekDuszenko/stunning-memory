package ui

import model.GithubClass
import model.GithubClasses

class HistogramView(private val histogram: MutableMap<String, Long>) {
    companion object {
        const val clearConsoleEscapeSequence: String = "\u001b[H\u001b[2J"
        const val unitLongValue = 1L
    }

    fun mergeToHistogram(githubClasses: GithubClasses) {
        for (githubClass in githubClasses.items) {
            mergeSingleNameToHistogram(githubClass)
        }
    }

    private fun mergeSingleNameToHistogram(githubClass: GithubClass) {
        histogram.merge(githubClass.name, unitLongValue) { a: Long, b: Long -> a + b }
    }

    fun clearDisplay() {
        print(clearConsoleEscapeSequence)
    }

    fun print() {
        val sortedByPopularity = getSortedListFromHistogram()
        for (entry in sortedByPopularity) {
            println("classname: ${entry.key}, occurrences: ${entry.value}")
        }
    }

    private fun getSortedListFromHistogram(): List<Map.Entry<String, Long>> {
        return histogram.entries.toList().sortedByDescending { entry -> entry.value }
    }
}