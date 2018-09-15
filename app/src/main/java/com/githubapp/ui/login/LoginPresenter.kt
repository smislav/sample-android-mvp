package com.githubapp.ui.login

import com.githubapp.ui.base.BasePresenter
import javax.inject.Inject


class LoginPresenter() : BasePresenter<ILoginView>(), ILoginPresenter {
    private lateinit var loginManager: LoginManager

    @Inject
    constructor(loginManager: LoginManager): this(){
        this.loginManager = loginManager
    }

    override fun login(email: String, password: String) {
        getView()?.showLoading()
        loginManager.login(email, password, object : LoginManager.LoginListener{
            override fun onLoginSucceeded() {
                getView()?.loginSucceeded()
                getView()?.hideLoading()
            }

            override fun onAccessError() {
                getView()?.loginFailed()
                getView()?.hideLoading()
            }

            override fun onNetworkError() {
                getView()?.errorNetwork()
                getView()?.hideLoading()
            }

            override fun onUnknownError() {
                getView()?.errorUnknown()
                getView()?.hideLoading()
            }
        })
    }
}
