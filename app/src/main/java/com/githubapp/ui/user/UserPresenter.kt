package com.githubapp.ui.user

import com.githubapp.data.models.User
import com.githubapp.data.source.DataSource
import com.githubapp.helpers.SimpleCallbackWrapper
import com.githubapp.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserPresenter() : BasePresenter<IUserView>(), IUserPresenter {
    private lateinit var mDataSource: DataSource

    @Inject
    constructor(dataSource: DataSource) : this() {
        mDataSource = dataSource
    }

    override fun getUser(username: String) {
        getView()?.showLoading()
        mDataSource.getUser(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: SimpleCallbackWrapper<User>(){
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
}