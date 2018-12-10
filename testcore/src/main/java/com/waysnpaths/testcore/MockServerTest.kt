package com.waysnpaths.testcore

import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before

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
}