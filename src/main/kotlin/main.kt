import factory.GithubServiceFactory
import factory.SearchQueryFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.ProgrammingLanguage.JAVA
import model.ReposChunk
import service.RepoService
import ui.Histogram
import java.util.*
import kotlin.collections.HashMap

fun main() {
    val repoService = RepoService(GithubServiceFactory().createDefaultGithubService(), SearchQueryFactory())
    val reposChannel: Channel<ReposChunk> = Channel(UNLIMITED)
    val histogram = Histogram(HashMap())
    val refreshDelayInMillis = 2000L

    runBlocking(Dispatchers.Default) {
        histogram.init(refreshDelayInMillis)
//        DateRange.getAllDatesFromYearAgo().forEach {
//            fetchReposFromGithub(repoService, it, reposChannel)
//
    }
}


private fun CoroutineScope.fetchReposFromGithub(
    repoService: RepoService,
    it: Date,
    reposChannel: Channel<ReposChunk>
) {
    launch {
        val allReposFromDay = repoService.getAllLanguageReposCreatedOnGivenDay(it, JAVA)
        reposChannel.send(allReposFromDay)
    }
}
