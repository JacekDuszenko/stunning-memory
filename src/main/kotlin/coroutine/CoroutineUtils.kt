package coroutine

import kotlinx.coroutines.delay
import service.GithubService

 suspend fun handleRateLimiting(githubService: GithubService) {
    val limitExceeded: Pair<Boolean, Long?> = githubService.checkCallLimitExceeded()
    if (limitExceeded.first) {
        val timeToWaitInMillis = calculateRateLimitWait(limitExceeded)
        delay(5000)
    }
}

private fun calculateRateLimitWait(limitExceeded: Pair<Boolean, Long?>) =
    limitExceeded.second ?: 0 - System.currentTimeMillis()
