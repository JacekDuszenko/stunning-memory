package model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ReposChunk(val items: List<Repo>)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Repo(val id: Long, val name: String, @JsonProperty("html_url") val htmlUrl: String)