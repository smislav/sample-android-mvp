package com.githubapp.ui.login

import com.githubapp.ui.base.IBaseView

interface ILoginView : IBaseView {
    fun loginSucceeded()
    fun loginFailed()
}