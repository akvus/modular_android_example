package com.waysnpaths.testcore


import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.functions.Function
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

import java.util.concurrent.Callable

/**
 * Credits: https://github.com/Plastix/RxSchedulerRule/blob/master/rx2/src/main/java/io/github/plastix/rxschedulerrule/RxSchedulerRule.java
 */
class RxSchedulerRule : TestRule {

    private val scheduler = Schedulers.trampoline()

    private val schedulerFunction = Function<Scheduler, Scheduler> { scheduler }

    private val schedulerFunctionLazy = Function<Callable<Scheduler>, Scheduler> { scheduler }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxAndroidPlugins.reset()
                RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerFunctionLazy)

                RxJavaPlugins.reset()
                RxJavaPlugins.setIoSchedulerHandler(schedulerFunction)
                RxJavaPlugins.setNewThreadSchedulerHandler(schedulerFunction)
                RxJavaPlugins.setComputationSchedulerHandler(schedulerFunction)

                base.evaluate()

                RxAndroidPlugins.reset()
                RxJavaPlugins.reset()
            }
        }
    }
}
