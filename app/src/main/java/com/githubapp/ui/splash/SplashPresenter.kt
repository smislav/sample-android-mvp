package com.githubapp.ui.splash

import com.githubapp.ui.base.BasePresenter
import com.githubapp.ui.login.LoginManager
import javax.inject.Inject

class SplashPresenter() : BasePresenter<ISplashView>(), ISplashPresenter {
    private lateinit var mLoginManager: LoginManager;

    @Inject
    constructor(loginManager: LoginManager): this(){
        mLoginManager = loginManager
    }

    override fun checkLogin() {
        val authorization = mLoginManager.getAuthorization()
        if(authorization.isAuthorized()){
            getView()?.navigateToMain()
        }else{
            getView()?.navigateToLogin()
        }
    }
}