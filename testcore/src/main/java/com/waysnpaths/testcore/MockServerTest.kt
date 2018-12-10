package com.waysnpaths.testcore

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import java.io.File

open class MockServerTest : BaseTest() {

    lateinit var mockServer: MockWebServer

    @Before
    fun setUp() {
        mockServer = MockWebServer()
        mockServer.start()

    }

    @After
    @Throws
    fun tearDown() {
        mockServer.shutdown()
    }

    @Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    fun getJson(path : String) : String {
        return String(File(javaClass.classLoader.getResource(path).path).readBytes())
    }
}