package com.githubapp.ui.profile

import com.githubapp.data.models.User
import com.githubapp.helpers.SimpleCallbackWrapper
import com.githubapp.ui.base.BasePresenter
import com.githubapp.ui.login.LoginManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ProfilePresenter() : BasePresenter<IProfileView>(), IProfilePresenter {
    private lateinit var loginManager: LoginManager

    @Inject
    constructor(loginManager: LoginManager) : this() {
        this.loginManager = loginManager
    }

    override fun getAuthenticatedUser() {
        getView()?.showLoading()
        loginManager.user()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SimpleCallbackWrapper<User>(){
                    override fun onNext(t: User) {
                        getView()?.loadUser(t)
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