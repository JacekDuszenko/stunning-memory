package service

import model.GithubClasses
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ClassesService {
    @GET("search/code")
    suspend fun getClasses(@Query("q") searchQuery: String): Response<GithubClasses>
}
