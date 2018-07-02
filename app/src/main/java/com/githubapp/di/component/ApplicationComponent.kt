package com.githubapp.di.component

import android.app.Application
import com.githubapp.GithubApp
import com.githubapp.di.module.ApplicationBinding
import com.githubapp.di.module.ApplicationModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Component(modules = [
    (AndroidInjectionModule::class),
    (ApplicationBinding::class),
    (ApplicationModule::class)])
@Singleton
interface ApplicationComponent {
    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): ApplicationComponent
    }

    fun inject(app: GithubApp)
}