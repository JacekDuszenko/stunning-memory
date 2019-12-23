package factory

import java.util.*

class SearchQueryFactory {
    fun createGithubSearchQuery(createdOn: Date, language: String): String {
        return "language:${language} created:${createdOn}"
    }


}