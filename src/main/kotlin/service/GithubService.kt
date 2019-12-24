package service

import model.AllReposResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("search/repositories")
    suspend fun getAllReposFromGivenDate(@Query("q") githubSearchQuery: String): AllReposResponse
}