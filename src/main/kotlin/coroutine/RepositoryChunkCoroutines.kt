package coroutine

import factory.DateRangeFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import model.ProgrammingLanguage
import model.ReposChunk
import service.GithubService
import java.util.*

fun CoroutineScope.fetchRepoMetadata(githubService: GithubService, reposChannel: Channel<ReposChunk>) {
    DateRangeFactory.getAllDatesFromYearAgo().forEach {
        launch {
            handleRateLimiting(githubService)
            fetchReposFromGithub(githubService, it, reposChannel)
        }
    }
}

private fun CoroutineScope.fetchReposFromGithub(
    githubService: GithubService,
    it: Date,
    reposChannel: Channel<ReposChunk>
) {
    launch {
        val allReposFromDay = githubService.getAllLanguageReposCreatedOnGivenDay(it, ProgrammingLanguage.JAVA)
        reposChannel.send(allReposFromDay)
    }
}