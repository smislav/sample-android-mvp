package com.githubapp.ui.login

import com.githubapp.ui.base.BasePresenter
import javax.inject.Inject


class LoginPresenter() : BasePresenter<ILoginView>(), ILoginPresenter {
    private lateinit var mLoginManager: LoginManager

    @Inject
    constructor(loginManager: LoginManager): this(){
        mLoginManager = loginManager
    }

    override fun login(email: String, password: String) {
        getView()?.showLoading()
        mLoginManager.login(email, password, object : LoginManager.LoginListener{
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
