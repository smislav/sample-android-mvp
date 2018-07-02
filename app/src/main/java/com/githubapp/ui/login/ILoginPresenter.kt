package com.githubapp.ui.login

import com.githubapp.ui.base.IBasePresenter
import com.githubapp.ui.login.ILoginView

interface ILoginPresenter : IBasePresenter<ILoginView> {
    fun login(email: String, password: String)
}