package coroutine

import factory.DateRangeFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import model.ProgrammingLanguage
import model.ReposChunk
import service.RepoService
import java.util.*

fun CoroutineScope.startFetchingReposFromGithub(
    repoService: RepoService,
    reposChannel: Channel<ReposChunk>
) {
    DateRangeFactory.getAllDatesFromYearAgo().forEach {
        fetchReposFromGithub(repoService, it, reposChannel)
    }
}


private fun CoroutineScope.fetchReposFromGithub(
    repoService: RepoService,
    it: Date,
    reposChannel: Channel<ReposChunk>
) {
    launch {
        val allReposFromDay = repoService.getAllLanguageReposCreatedOnGivenDay(it, ProgrammingLanguage.JAVA)
        reposChannel.send(allReposFromDay)
    }
}