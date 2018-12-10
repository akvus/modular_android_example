package com.waysnpaths.core.remote

import com.waysnpaths.testcore.MockServerTest
import okhttp3.mockwebserver.MockResponse
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class RemoteClientTest : MockServerTest() {
    private val remoteInterface: RemoteInterface = RemoteClient.remoteInterface

    @Test
    fun getIssues() {
        mockServer.enqueue(MockResponse().apply {
            setResponseCode(200)
            setBody(getJson("json/remote/issues.json"))
        })

        val page = 3
        val result = remoteInterface.getIssues(page)
            .test()
            .values()

        assertThat(result[0][0].state, equalTo("open"))
    }
}