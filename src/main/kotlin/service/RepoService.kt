package service

import factory.SearchQueryFactory
import model.AllReposResponse
import model.ProgrammingLanguage
import java.util.*

class RepoService(private val githubService: GithubService, private val searchQueryFactory: SearchQueryFactory) {

    suspend fun getAllLanguageReposCreatedOnGivenDay(createdOn: Date, programmingLanguage: ProgrammingLanguage): AllReposResponse {
        val searchQuery: String = searchQueryFactory.createGithubSearchQuery(createdOn, programmingLanguage)
        return githubService.getAllReposFromGivenDate(searchQuery)
    }
}