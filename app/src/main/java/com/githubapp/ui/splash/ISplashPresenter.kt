package com.githubapp.ui.splash

import com.githubapp.ui.base.IBasePresenter

interface ISplashPresenter : IBasePresenter<ISplashView> {
    fun checkLogin()
}