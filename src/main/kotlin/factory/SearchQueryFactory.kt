package factory

import model.ProgrammingLanguage
import java.text.SimpleDateFormat
import java.util.*

class SearchQueryFactory {
    companion object {
        const val dateFormatString: String = "yyyy-MM-dd"
    }

    fun createChunkRepoSearchQuery(createdOn: Date, language: ProgrammingLanguage): String {
        val sdf = SimpleDateFormat(dateFormatString)

        return "language:${language.languageName} created:${sdf.format(createdOn)}"
    }

    fun createClassesSearchQuery(programmingLanguage: ProgrammingLanguage, repositoryName: String): String {
        return "class in:file language:${programmingLanguage.languageName} repo:${repositoryName} extension:${programmingLanguage.extension}"
    }
}