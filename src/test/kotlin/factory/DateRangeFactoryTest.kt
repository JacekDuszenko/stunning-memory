package factory

import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import java.util.*

class DateRangeFactoryTest {
    companion object {
        private const val dayInMs: Long = 1000 * 60 * 60 * 24
    }

    @Test
    fun shouldGetSevenDaysRange() {
        val weekAgo = getDateSevenDaysAgo()
        val validLengthOfList = 7

        assertThat(DateRangeFactory.getAllDatesUntilNow(weekAgo).size, CoreMatchers.equalTo(validLengthOfList))
    }

    private fun getDateSevenDaysAgo() = Date(System.currentTimeMillis() - 7 * dayInMs)

    @Test(expected = Exception::class)
    fun shouldThrowExceptionWhenInvalidDateRange() {
        val tomorrow = getMonthAfterNow()
        DateRangeFactory.getAllDatesUntilNow(tomorrow)
    }

    private fun getMonthAfterNow() = Date(System.currentTimeMillis() + 31 *  dayInMs)
}