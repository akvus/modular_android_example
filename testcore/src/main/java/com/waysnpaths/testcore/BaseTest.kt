package com.waysnpaths.testcore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

abstract class BaseTest {

    @get:Rule
    val schedulerRule = RxSchedulerRule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()
}