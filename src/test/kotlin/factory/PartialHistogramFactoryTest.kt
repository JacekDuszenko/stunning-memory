package factory

import model.GithubClass
import model.GithubClasses
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertThat
import org.junit.Test

class PartialHistogramFactoryTest {

    @Test
    fun shouldCreateValidPartialHistogramFromExampleData() {
        //given
        val validClasses: GithubClasses = createValidGithubClasses()
        //when
        val map = PartialHistogramFactory().createPartialHistogramFromClasses(validClasses)
        //then
        assertThat(map["testClass1"], CoreMatchers.equalTo(2L))
        assertThat(map["testClass2"], CoreMatchers.equalTo(1L))

    }

    private fun createValidGithubClasses() =
        GithubClasses(
            mutableListOf(
                GithubClass("testClass1"),
                GithubClass("testClass1"),
                GithubClass("testClass2")
            )
        )
}