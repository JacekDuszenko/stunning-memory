package factory

import model.GithubClasses

class PartialHistogramFactory {

    fun createPartialHistogramFromClasses(classes: GithubClasses): Map<String, Long> {
        return classes.items.groupingBy { it.name }.eachCount().mapValues { it.value.toLong() }
    }
}