package com.waysnpaths.core.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides


@Module
class CoreModule {
    @Provides
    fun context(application: Application): Context = application
}