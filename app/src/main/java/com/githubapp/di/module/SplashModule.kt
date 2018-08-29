package com.githubapp.di.module

import com.githubapp.di.scope.ActivityScope
import com.githubapp.ui.login.LoginManager
import com.githubapp.ui.splash.ISplashPresenter
import com.githubapp.ui.splash.SplashPresenter
import dagger.Module
import dagger.Provides

@Module
class SplashModule {
    @Provides
    @ActivityScope
    fun provideSplashPresenter(loginManager: LoginManager): ISplashPresenter{
        return SplashPresenter(loginManager)
    }
}