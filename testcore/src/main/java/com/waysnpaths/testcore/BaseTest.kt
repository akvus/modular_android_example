package com.waysnpaths.testcore

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import java.io.File

abstract class BaseTest {

    @get:Rule
    val schedulerRule = RxSchedulerRule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun getJson(path : String) : String {
        return String(File(javaClass.classLoader.getResource(path).path).readBytes())
    }
}