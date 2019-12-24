package service

import factory.SearchQueryFactory
import model.GithubClasses
import model.ProgrammingLanguage
import model.ReposChunk
import java.util.*

class GithubService(private val chunkRepoService: ChunkRepoService, private val classesService: ClassesService, private val searchQueryFactory: SearchQueryFactory) {

    suspend fun getAllLanguageReposCreatedOnGivenDay(createdOn: Date, programmingLanguage: ProgrammingLanguage): ReposChunk {
        val searchQuery: String = searchQueryFactory.createChunkRepoSearchQuery(createdOn, programmingLanguage)
        val response = chunkRepoService.getAllReposFromGivenDate(searchQuery)
        return response.body() ?: emptyReposChunk()
    }

    suspend fun getAllLanguageClassesInRepository(programmingLanguage: ProgrammingLanguage, repositoryName: String): GithubClasses {
        val searchQuery: String = searchQueryFactory.createClassesSearchQuery(programmingLanguage, repositoryName)
        val response = classesService.getClasses(searchQuery)
        return response.body() ?: emptyClasses()
    }

    private fun emptyReposChunk() = ReposChunk(mutableListOf())
    private fun emptyClasses() = GithubClasses(mutableListOf())
}