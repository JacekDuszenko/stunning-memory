package factory

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import service.ChunkRepoService
import service.ClassesService
import service.GithubService
import service.RateLimitService
import java.util.*

class GithubServiceFactory {

    fun createDefaultRepoService(login: String, password: String): GithubService {
        return GithubService(
            createDefaultChunkRepoService(login, password),
            createDefaultClassesService(login, password),
            createRateLimitService(login, password),
            SearchQueryFactory()
        )
    }

    private fun createDefaultChunkRepoService(login: String, password: String): ChunkRepoService {
        val httpClient = createHttpClient(login, password)
        val retrofit = createRetrofit(httpClient)
        return retrofit.create(ChunkRepoService::class.java)
    }

    private fun createDefaultClassesService(login: String, password: String): ClassesService {
        val httpClient = createHttpClient(login, password)
        val retrofit = createRetrofit(httpClient)
        return retrofit.create(ClassesService::class.java)
    }

    private fun createRateLimitService(login: String, password: String): RateLimitService {
        val httpClient = createHttpClient(login, password)
        val retrofit = createRetrofit(httpClient)
        return retrofit.create(RateLimitService::class.java)
    }

    private fun createRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .addConverterFactory(JacksonConverterFactory.create(jacksonObjectMapper()))
            .client(httpClient)
            .build()
    }

    private fun createHttpClient(login: String, password: String): OkHttpClient {
        val authToken = "Basic " + Base64.getEncoder().encode("${login}:${password}".toByteArray()).toString(Charsets.UTF_8)
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val builder = original.newBuilder()
                    .header("Accept", "application/vnd.github.v3+json")
                    .header("Authorization", authToken)
                val request = builder.build()
                chain.proceed(request)
            }
            .build()
    }
}
