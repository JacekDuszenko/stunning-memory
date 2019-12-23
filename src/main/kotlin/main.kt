import factory.createGithubService
import model.AllReposResponse
import retrofit2.Call
import service.GithubService

fun main() {
    val githubService: GithubService = createGithubService()
    val allReposCall: Call<AllReposResponse> = githubService.getAllJavaReposFromGivenDate("language:Java created:2019-12-21")
    val allRepos: AllReposResponse = allReposCall.execute().body() ?: AllReposResponse(mutableListOf())
    for (repo in allRepos.items) {
        println(repo)
    }

}