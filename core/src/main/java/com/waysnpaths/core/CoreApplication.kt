package com.waysnpaths.core

import com.squareup.leakcanary.LeakCanary
import com.waysnpaths.core.di.CoreComponent
import com.waysnpaths.core.di.DaggerCoreComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber

class CoreApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        coreComponent = DaggerCoreComponent.builder().application(this).build()
        return coreComponent
    }

    override fun onCreate() {
        super.onCreate()
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return
        }
        LeakCanary.install(this)

        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var coreComponent: CoreComponent
            private set
    }
}