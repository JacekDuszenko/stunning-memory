package service

import model.ReposChunk
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ChunkRepoService {
    @GET("search/repositories")
    suspend fun getAllReposFromGivenDate(@Query("q") githubSearchQuery: String): Response<ReposChunk>
}