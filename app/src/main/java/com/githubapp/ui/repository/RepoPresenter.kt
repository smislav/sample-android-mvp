package com.githubapp.ui.repository

import com.githubapp.data.models.Repo
import com.githubapp.data.source.DataSource
import com.githubapp.helpers.SimpleCallbackWrapper
import com.githubapp.ui.base.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class RepoPresenter() : BasePresenter<IRepoView>(), IRepoPresenter {
    private lateinit var dataSource: DataSource

    @Inject
    constructor(dataSource: DataSource) : this() {
        this.dataSource = dataSource
    }

    override fun getRepo(username: String, repoName: String) {
        getView()?.showLoading()
        dataSource.getRepo(username, repoName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: SimpleCallbackWrapper<Repo>(){
                    override fun onNext(t: Repo) {
                        getView()?.loadRepo(t)
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