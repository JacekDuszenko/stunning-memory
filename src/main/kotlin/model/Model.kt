package model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ReposChunk(val items: List<Repo>)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Repo(val id: Long, @JsonProperty("full_name") val fullName: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class GithubClasses(val items: List<GithubClass>)

@JsonIgnoreProperties(ignoreUnknown = true)
data class GithubClass(val name: String)

@JsonIgnoreProperties(ignoreUnknown = true)
data class RateLimit(val resources: SearchRateLimit)

@JsonIgnoreProperties(ignoreUnknown = true)
data class SearchRateLimit(val search: Limits)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Limits(val remaining: Int, val reset: Long)