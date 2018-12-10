package com.waysnpaths.core.domain.useCase

import com.waysnpaths.core.remote.user.RemoteUserMapper
import com.waysnpaths.testcore.TestData
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class MapperTest {

    private val mapper = RemoteUserMapper()

    @Test
    fun mapList() {
        val users = mapper.mapList(TestData.remoteUsers)

        assertThat(users[0].login, equalTo(TestData.users[0].login))
    }
}