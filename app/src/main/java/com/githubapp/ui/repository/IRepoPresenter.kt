package com.githubapp.ui.repository

import com.githubapp.ui.base.IBasePresenter

interface IRepoPresenter : IBasePresenter<IRepoView> {
    fun getRepo(username: String, repoName: String)
}