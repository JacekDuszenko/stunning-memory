package service

import factory.SearchQueryFactory
import model.GithubClasses
import model.ProgrammingLanguage
import model.RateLimit
import model.ReposChunk
import java.util.*

class GithubService(private val chunkRepoService: ChunkRepoService,
                    private val classesService: ClassesService,
                    private val rateLimitService: RateLimitService,
                    private val searchQueryFactory: SearchQueryFactory) {

    suspend fun getAllLanguageReposCreatedOnGivenDay(createdOn: Date, programmingLanguage: ProgrammingLanguage): ReposChunk {
        val searchQuery: String = searchQueryFactory.createChunkRepoSearchQuery(createdOn, programmingLanguage)
        val response = chunkRepoService.getAllReposFromGivenDate(searchQuery)
        return response.body() ?: emptyReposChunk()
    }

    suspend fun getAllLanguageClassesInRepository(programmingLanguage: ProgrammingLanguage, repositoryName: String): GithubClasses {
        val searchQuery: String = searchQueryFactory.createClassesSearchQuery(programmingLanguage, repositoryName)
        val response = classesService.getClasses(searchQuery)
        return response.body() ?: emptyClasses()
    }

    suspend fun checkCallLimitExceeded(): Pair<Boolean,Long?> {
        val response = rateLimitService.getRateLimit()
        val rateLimit: RateLimit? = response.body()
        val numberOfAvailableRequests = rateLimit?.resources?.search?.remaining
        val limitExceeded: Boolean = numberOfAvailableRequests != null && numberOfAvailableRequests == 0
        val remainingTimeToUnlockLimit: Long? = rateLimit?.resources?.search?.reset
        return Pair(limitExceeded, remainingTimeToUnlockLimit)
    }

    private fun emptyReposChunk() = ReposChunk(mutableListOf())
    private fun emptyClasses() = GithubClasses(mutableListOf())
}