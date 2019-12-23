package service

import model.AllReposResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    companion object {
        private val baseJavaRepositoriesUrl: String = "search/repositories?q=language:Java"
    }

    @GET("search/repositories")
    fun getAllJavaReposFromGivenDate(@Query("q") githubSearchQuery: String): Call<AllReposResponse>
}