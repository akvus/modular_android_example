package com.waysnpaths.testcore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import timber.log.Timber

abstract class BaseTest {

    @get:Rule
    val schedulerRule = RxSchedulerRule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()
}