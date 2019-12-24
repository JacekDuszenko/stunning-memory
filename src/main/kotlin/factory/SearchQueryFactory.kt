package factory

import model.ProgrammingLanguage
import java.text.SimpleDateFormat
import java.util.*

class SearchQueryFactory {
    companion object {
        const val dateFormatString: String = "yyyy-MM-dd"
    }

    fun createGithubSearchQuery(createdOn: Date, language: ProgrammingLanguage): String {
        val sdf = SimpleDateFormat(dateFormatString)

        return "language:${language.languageName} created:${sdf.format(createdOn)}"
    }
}