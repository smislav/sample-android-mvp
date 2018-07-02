package com.githubapp.ui.user

import com.githubapp.ui.base.IBasePresenter

interface IUserPresenter : IBasePresenter<IUserView> {
    fun getUser(username: String)
}