package com.githubapp.ui.splash

import com.githubapp.ui.base.BasePresenter
import com.githubapp.ui.login.LoginManager
import javax.inject.Inject

class SplashPresenter() : BasePresenter<ISplashView>(), ISplashPresenter {
    private lateinit var loginManager: LoginManager;

    @Inject
    constructor(loginManager: LoginManager): this(){
        this.loginManager = loginManager
    }

    override fun checkLogin() {
        val logged = loginManager.isLogged()
        if(logged){
            getView()?.navigateToMain()
        }else{
            getView()?.navigateToLogin()
        }
    }
}