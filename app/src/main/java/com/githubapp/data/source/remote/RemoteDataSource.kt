package com.githubapp.data.source.remote

import com.githubapp.api.GithubApi
import com.githubapp.data.models.Repo
import com.githubapp.data.models.User
import com.githubapp.data.source.IDataSource
import com.githubapp.ui.login.LoginManager
import io.reactivex.Observable
import javax.inject.Inject

class RemoteDataSource : IDataSource{
    var mGithubService : GithubApi
    var mLoginManager : LoginManager

    @Inject
    constructor(githubService: GithubApi, loginManager: LoginManager){
        mGithubService = githubService
        mLoginManager = loginManager
    }

    override fun getRepos(page: Int, query: String, sort: String): Observable<List<Repo>> {
        return mGithubService.getRepos(page, query, sort, mLoginManager.getAuthorization().token)
                .map { it -> it.items }
    }

    override fun getRepo(username: String, repoName: String): Observable<Repo> {
        return mGithubService.getRepo(username, repoName, mLoginManager.getAuthorization().token)
    }

    override fun getUser(username: String): Observable<User> {
        return mGithubService.getUser(username, mLoginManager.getAuthorization().token)
    }
}