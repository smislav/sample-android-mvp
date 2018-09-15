package com.githubapp.di.module

import com.githubapp.di.scope.ActivityScope
import com.githubapp.ui.login.ILoginPresenter
import com.githubapp.ui.login.LoginManager
import com.githubapp.ui.login.LoginPresenter
import dagger.Module
import dagger.Provides

@Module
class LoginModule {
    @Provides
    @ActivityScope
    fun provideLoginPresenter(loginManager: LoginManager): ILoginPresenter {
        return LoginPresenter(loginManager)
    }
}