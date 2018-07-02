package com.githubapp.ui.user

import com.githubapp.data.models.User
import com.githubapp.ui.base.IBaseView

interface IUserView : IBaseView {
    fun loadUser(user: User)
}