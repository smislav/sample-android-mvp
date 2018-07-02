package com.githubapp.ui.repository

import com.githubapp.data.models.Repo
import com.githubapp.data.models.User
import com.githubapp.ui.base.IBaseView

interface IRepoView : IBaseView {
    fun loadRepo(repo: Repo)
}