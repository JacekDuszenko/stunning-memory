import coroutine.startFetchingReposFromGithub
import coroutine.startVisitingSpecificRepository
import factory.GithubServiceFactory
import factory.SearchQueryFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.runBlocking
import model.ReposChunk
import service.RepoService
import ui.Histogram

fun main() {
    val serviceFactory = GithubServiceFactory()
    val repoService = RepoService(serviceFactory.createDefaultChunkRepoService(), serviceFactory.createDefaultClassesService(),SearchQueryFactory())
    val reposChannel: Channel<ReposChunk> = Channel(UNLIMITED)
    val histogram = Histogram(HashMap())
    val refreshDelayInMillis = 2000L

    runBlocking(Dispatchers.Default) {
        histogram.init(refreshDelayInMillis)
        startFetchingReposFromGithub(repoService, reposChannel)
        startVisitingSpecificRepository(repoService, reposChannel)
    }
}

