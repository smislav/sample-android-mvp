package com.githubapp.ui.profile

import com.githubapp.data.models.User
import com.githubapp.ui.base.IBaseView

interface IProfileView : IBaseView {
    fun loadUser(user: User)
    fun navigateToSplash()
}