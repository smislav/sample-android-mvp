package com.githubapp.di.module

import com.githubapp.di.scope.ActivityScope
import com.githubapp.ui.login.LoginActivity
import com.githubapp.ui.profile.ProfileActivity
import com.githubapp.ui.repository.RepoActivity
import com.githubapp.ui.search.SearchActivity
import com.githubapp.ui.splash.SplashActivity
import com.githubapp.ui.user.UserActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ApplicationBinding {
    @ContributesAndroidInjector(modules = [(LoginModule::class)])
    @ActivityScope
    abstract fun loginActivity(): LoginActivity

    @ContributesAndroidInjector(modules = [(SearchModule::class)])
    @ActivityScope
    abstract fun searchActivity(): SearchActivity

    @ContributesAndroidInjector(modules = [(SplashModule::class)])
    @ActivityScope
    abstract fun splashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [(UserModule::class)])
    @ActivityScope
    abstract fun userActivity(): UserActivity

    @ContributesAndroidInjector(modules = [(RepoModule::class)])
    @ActivityScope
    abstract fun repoActivity(): RepoActivity

    @ContributesAndroidInjector(modules = [(ProfileModule::class)])
    @ActivityScope
    abstract fun profileActivity(): ProfileActivity
}