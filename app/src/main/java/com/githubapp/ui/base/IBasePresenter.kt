package com.githubapp.ui.base

interface IBasePresenter<V: IBaseView> {
    fun attachView(view: V)
    fun getView(): V?
    fun detachView()
}