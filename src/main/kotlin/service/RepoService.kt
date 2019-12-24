package service

import factory.SearchQueryFactory
import model.ProgrammingLanguage
import model.ReposChunk
import java.util.*

class RepoService(private val githubService: GithubService, private val searchQueryFactory: SearchQueryFactory) {

    suspend fun getAllLanguageReposCreatedOnGivenDay(createdOn: Date, programmingLanguage: ProgrammingLanguage): ReposChunk {
        val searchQuery: String = searchQueryFactory.createGithubSearchQuery(createdOn, programmingLanguage)
        val response = githubService.getAllReposFromGivenDate(searchQuery)
        if (! response.isSuccessful) {
            println(response.code())
        }
        return response.body() ?: emptyReposChunk()
    }

    private fun emptyReposChunk() = ReposChunk(mutableListOf())
}