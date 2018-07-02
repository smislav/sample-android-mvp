package com.githubapp.di.module

import com.githubapp.api.GithubApi
import com.githubapp.ui.login.LoginManager
import com.githubapp.di.scope.ActivityScope
import com.githubapp.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @Provides
    @ActivityScope
    fun provideLoginPresenter(loginManager: LoginManager): LoginPresenter{
        return LoginPresenter(loginManager)
    }
}