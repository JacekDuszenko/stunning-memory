package service

import model.RateLimit
import retrofit2.Response
import retrofit2.http.GET

interface RateLimitService {
    @GET("rate_limit")
    suspend fun getRateLimit(): Response<RateLimit>

}