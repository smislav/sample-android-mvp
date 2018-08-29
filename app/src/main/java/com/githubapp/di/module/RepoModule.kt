package com.githubapp.di.module

import com.githubapp.data.source.DataSource
import com.githubapp.di.scope.ActivityScope
import com.githubapp.ui.repository.IRepoPresenter
import com.githubapp.ui.repository.RepoPresenter
import dagger.Module
import dagger.Provides

@Module
class RepoModule {
    @Provides
    @ActivityScope
    fun provideRepoPresenter(dataSource: DataSource): IRepoPresenter{
        return RepoPresenter(dataSource)
    }
}