package com.githubapp.di.module

import com.githubapp.data.source.DataSource
import com.githubapp.di.scope.ActivityScope
import com.githubapp.ui.splash.SplashPresenter
import com.githubapp.ui.user.UserPresenter
import dagger.Module
import dagger.Provides

@Module
class UserModule {
    @Provides
    @ActivityScope
    fun provideUserPresenter(dataSource: DataSource): UserPresenter{
        return UserPresenter(dataSource)
    }
}