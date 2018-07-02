package com.githubapp.ui.search

import com.githubapp.data.models.Repo
import com.githubapp.ui.base.IBaseView

interface ISearchView : IBaseView {
        fun clearRepos()
    fun addRepos(repos: List<Repo>)
    fun navigateToProfile()
    fun navigateToUser(username: String)
    fun navigateToRepo(username: String, repoName: String)
}