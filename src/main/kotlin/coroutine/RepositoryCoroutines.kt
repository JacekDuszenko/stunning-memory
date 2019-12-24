package coroutine

import factory.PartialHistogramFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import model.ProgrammingLanguage
import model.Repo
import model.ReposChunk
import service.RepoService

fun CoroutineScope.startVisitingSpecificRepository(repoService: RepoService, reposChannel: Channel<ReposChunk>): Job {
    return launch {
        for (repos in reposChannel) {
            for (singleRepo in repos.items) {
                visitRepository(repoService, singleRepo)
            }
        }
    }
}

private fun CoroutineScope.visitRepository(repoService: RepoService, singleRepo: Repo) {
    launch {
        val classes = repoService.getAllLanguageClassesInRepository(ProgrammingLanguage.JAVA, singleRepo.fullName)
        val partialHistogram = PartialHistogramFactory().createPartialHistogramFromClasses(classes)

    }
}


