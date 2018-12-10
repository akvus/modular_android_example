package com.waysnpaths.core.di

import android.app.Application
import android.content.Context
import com.waysnpaths.core.CoreApplication
import com.waysnpaths.core.domain.useCase.issue.GetIssuesUseCase
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@CoreScope
@Component(modules = [CoreModule::class, RemoteModule::class, UseCaseModule::class, AndroidInjectionModule::class])
interface CoreComponent : AndroidInjector<CoreApplication> {

    fun context(): Context
    fun getIssuesUseCase(): GetIssuesUseCase

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): CoreComponent
    }

    fun inject(application: Application)

    companion object {
        lateinit var instance: CoreComponent
    }
}