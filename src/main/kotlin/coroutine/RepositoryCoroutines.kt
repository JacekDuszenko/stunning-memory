package coroutine

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.SendChannel
import kotlinx.coroutines.launch
import model.GithubClasses
import model.ProgrammingLanguage
import model.Repo
import model.ReposChunk
import service.GithubService
import ui.HistogramView

fun CoroutineScope.visitRepo(githubService: GithubService, reposChannel: ReceiveChannel<ReposChunk>, classesChannel: SendChannel<GithubClasses>): Job {
    return launch {
        for (repos in reposChannel) {
            for (singleRepo in repos.items) {
                visitRepository(githubService, singleRepo, classesChannel)
            }
        }
    }
}

private fun CoroutineScope.visitRepository(githubService: GithubService, singleRepo: Repo, classesChannel: SendChannel<GithubClasses>) {
    launch {
        val classes = githubService.getAllLanguageClassesInRepository(ProgrammingLanguage.JAVA, singleRepo.fullName)
        classesChannel.send(classes)
    }
}

fun CoroutineScope.mergeToHistogram(classesChannel: Channel<GithubClasses>, histogramView: HistogramView) {
    launch {
        for (githubClasses in classesChannel) {
            histogramView.mergeToHistogram(githubClasses)
        }
    }
}


