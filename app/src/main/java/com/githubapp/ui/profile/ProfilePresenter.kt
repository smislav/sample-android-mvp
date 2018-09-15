package com.githubapp.ui.profile

import com.githubapp.data.models.User
import com.githubapp.ui.base.BasePresenter
import com.githubapp.ui.login.LoginManager
import javax.inject.Inject

class ProfilePresenter() : BasePresenter<IProfileView>(), IProfilePresenter {
    private lateinit var loginManager: LoginManager

    @Inject
    constructor(loginManager: LoginManager) : this() {
        this.loginManager = loginManager
    }

    override fun getAuthenticatedUser() {
        getView()?.showLoading()
        loginManager.getUser(object : LoginManager.UserListener{
            override fun onUserRetrieved(user: User) {
                getView()?.loadUser(user)
                getView()?.hideLoading()
            }

            override fun onNetworkError() {
                getView()?.errorNetwork()
                getView()?.hideLoading()
            }

            override fun onAccessError() {
                getView()?.errorAccess()
                getView()?.hideLoading()
            }

            override fun onUnknownError() {
                getView()?.errorUnknown()
                getView()?.hideLoading()
            }
        })

    }


    override fun logout() {
        loginManager.logout()
        getView()?.navigateToSplash()
    }
}