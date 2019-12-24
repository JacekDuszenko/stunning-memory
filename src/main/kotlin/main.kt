import factory.DateRange
import factory.GithubServiceFactory
import factory.SearchQueryFactory
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import model.ReposChunk
import model.ProgrammingLanguage.JAVA
import service.RepoService

fun main() {
    val repoService = RepoService(GithubServiceFactory().createDefaultGithubService(), SearchQueryFactory())
    val reposChannel: Channel<ReposChunk> = Channel(UNLIMITED)

    runBlocking {
        DateRange.getAllDatesFromYearAgo().forEach {
            launch {
                print("XD?")
                val allReposFromDay = repoService.getAllLanguageReposCreatedOnGivenDay(it, JAVA)
                reposChannel.send(allReposFromDay)
            }
        }
    }

}

