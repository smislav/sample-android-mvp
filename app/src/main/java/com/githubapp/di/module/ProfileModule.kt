package com.githubapp.di.module

import com.githubapp.data.source.DataSource
import com.githubapp.di.scope.ActivityScope
import com.githubapp.ui.login.LoginManager
import com.githubapp.ui.profile.ProfilePresenter
import com.githubapp.ui.splash.SplashPresenter
import com.githubapp.ui.user.UserPresenter
import dagger.Module
import dagger.Provides

@Module
class ProfileModule {
    @Provides
    @ActivityScope
    fun provideProfilePresenter(loginManager: LoginManager): ProfilePresenter{
        return ProfilePresenter(loginManager)
    }
}