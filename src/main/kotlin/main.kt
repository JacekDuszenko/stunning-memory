import coroutine.display
import coroutine.fetchRepoMetadata
import coroutine.mergeToHistogram
import coroutine.visitRepo
import factory.GithubServiceFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.runBlocking
import model.GithubClasses
import model.ReposChunk
import service.GithubService
import ui.HistogramView

fun main() {
    val serviceFactory = GithubServiceFactory()
    val repoService = serviceFactory.createDefaultRepoService()
    val reposChannel: Channel<ReposChunk> = Channel(UNLIMITED)
    val classesChannel: Channel<GithubClasses> = Channel(UNLIMITED)
    val histogram = HistogramView(HashMap())

    init(histogram, repoService, reposChannel, classesChannel)
}

private fun init(histogramView: HistogramView, repoService: GithubService, reposChannel: Channel<ReposChunk>, classesChannel: Channel<GithubClasses>) =
    runBlocking(Dispatchers.Default) {
        display(histogramView)
        fetchRepoMetadata(repoService, reposChannel)
        visitRepo(repoService, reposChannel, classesChannel)
        mergeToHistogram(classesChannel, histogramView)
    }