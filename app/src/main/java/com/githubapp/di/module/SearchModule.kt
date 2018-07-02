package com.githubapp.di.module

import com.githubapp.data.source.DataSource
import com.githubapp.di.scope.ActivityScope
import com.githubapp.ui.search.SearchPresenter
import com.githubapp.ui.search.adapters.RepoAdapter
import dagger.Module
import dagger.Provides

@Module
class SearchModule {
    @Provides
    @ActivityScope
    fun provideSearchPresenter(dataSource: DataSource): SearchPresenter{
        return SearchPresenter(dataSource)
    }

    @Provides
    @ActivityScope
    fun provideRepoAdapter(): RepoAdapter{
        return RepoAdapter()
    }
}