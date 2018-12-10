package com.waysnpaths.core.remote.user

import com.waysnpaths.testcore.TestData
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class RemoteUserMapperTest {

    private val mapper = RemoteUserMapper()

    @Test
    fun map() {
        val user = mapper.map(TestData.remoteUser)
        assertThat(user.login, equalTo(TestData.remoteUser.login))
    }
}