package com.githubapp.di.module

import com.githubapp.di.scope.ActivityScope
import com.githubapp.ui.login.LoginManager
import com.githubapp.ui.profile.IProfilePresenter
import com.githubapp.ui.profile.ProfilePresenter
import dagger.Module
import dagger.Provides

@Module
class ProfileModule {
    @Provides
    @ActivityScope
    fun provideProfilePresenter(loginManager: LoginManager): IProfilePresenter{
        return ProfilePresenter(loginManager)
    }
}