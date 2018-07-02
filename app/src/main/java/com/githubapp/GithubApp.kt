package com.githubapp

import android.app.Activity
import android.app.Application
import com.githubapp.di.component.ApplicationComponent
import com.githubapp.di.component.DaggerApplicationComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class GithubApp() : Application(), HasActivityInjector {
    @Inject
    lateinit var mActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): DispatchingAndroidInjector<Activity> = mActivityInjector
}