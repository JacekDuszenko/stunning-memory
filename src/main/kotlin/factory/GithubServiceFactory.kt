package factory

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import service.ChunkRepoService
import service.ClassesService
import service.GithubService

class GithubServiceFactory {

    fun createDefaultRepoService(): GithubService {
        return GithubService(
            createDefaultChunkRepoService(),
            createDefaultClassesService(),
            SearchQueryFactory()
        )
    }

    private fun createDefaultChunkRepoService(): ChunkRepoService {
        val httpClient = createHttpClient()
        val retrofit = createRetrofit(httpClient)
        return retrofit.create(ChunkRepoService::class.java)
    }

    private fun createDefaultClassesService(): ClassesService {
        val httpClient = createHttpClient()
        val retrofit = createRetrofit(httpClient)
        return retrofit.create(ClassesService::class.java)
    }

    private fun createRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper()))
            .client(httpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .build()
    }
}
