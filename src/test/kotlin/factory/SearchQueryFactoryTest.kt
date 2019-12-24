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
    fun shouldCreateValidGithubQueryFromDate() {
        //given
        val validDateString = "2017-12-31"
        val date: Date = java.sql.Date.valueOf(LocalDate.parse(validDateString))
        val validQuery = "language:Java created:2017-12-31"
        //when
        val query: String = searchQueryFactory.createGithubSearchQuery(date, ProgrammingLanguage.JAVA)

        //then
        assertThat(query, CoreMatchers.equalTo(validQuery))
    }
}