package com.waysnpaths.core.di

import android.content.Context
import com.waysnpaths.core.util.StringRetriever
import dagger.Module
import dagger.Provides

@Module
class UtilsModule {
    @Provides
    fun stringRetriever(context: Context) = StringRetriever(context)
}