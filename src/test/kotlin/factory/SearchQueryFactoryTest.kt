package factory

import model.ProgrammingLanguage
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Test
import java.time.LocalDate
import java.util.*

class SearchQueryFactoryTest {

    private val searchQueryFactory: SearchQueryFactory = SearchQueryFactory()

    @Test
    fun shouldCreateValidChunkRepoQueryFromDate() {
        //given
        val validDateString = "2017-12-31"
        val date: Date = java.sql.Date.valueOf(LocalDate.parse(validDateString))
        val validQuery = "language:Java created:2017-12-31"
        //when
        val query: String = searchQueryFactory.createChunkRepoSearchQuery(date, ProgrammingLanguage.JAVA)

        //then
        assertThat(query, CoreMatchers.equalTo(validQuery))
    }

    @Test
    fun shouldCreteValidClassesQuery() {
        //given
        val language: ProgrammingLanguage = ProgrammingLanguage.JAVA
        val repoName = "repoUser/repo"
        val validQuery = "class+in:file+language:Java+repo:repoUser/repo+extension:.java"

        //when
        val query: String = searchQueryFactory.createClassesSearchQuery(language, repoName)

        //then
        assertThat(query, CoreMatchers.equalTo(validQuery))
    }
}