package com.githubapp.ui.login

import com.githubapp.api.models.Authorization
import com.githubapp.helpers.SimpleCallbackWrapper
import com.githubapp.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class LoginPresenter() : BasePresenter<ILoginView>(), ILoginPresenter {
    private lateinit var loginManager: LoginManager

    @Inject
    constructor(loginManager: LoginManager): this(){
        this.loginManager = loginManager
    }

    override fun login(email: String, password: String) {
        getView()?.showLoading()
        loginManager.login(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : SimpleCallbackWrapper<Authorization>(){
                    override fun onNext(t: Authorization) {
                        getView()?.loginSucceeded()
                        getView()?.hideLoading()
                    }

                    override fun onNetworkError() {
                        getView()?.errorNetwork()
                        getView()?.hideLoading()
                    }

                    override fun onAccessError() {
                        getView()?.loginFailed()
                        getView()?.hideLoading()
                    }

                    override fun onUnknownError() {
                        getView()?.errorUnknown()
                        getView()?.hideLoading()
                    }
                })
    }
}
