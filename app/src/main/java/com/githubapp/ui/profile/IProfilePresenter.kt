package com.githubapp.ui.profile

import com.githubapp.ui.base.IBasePresenter

interface IProfilePresenter : IBasePresenter<IProfileView> {
    fun getAuthenticatedUser()
    fun logout()
}