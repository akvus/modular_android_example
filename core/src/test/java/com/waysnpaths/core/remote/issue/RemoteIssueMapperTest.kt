package com.waysnpaths.core.remote.issue

import com.nhaarman.mockitokotlin2.whenever
import com.waysnpaths.core.remote.user.RemoteUserMapper
import com.waysnpaths.testcore.TestData
import com.waysnpaths.testcore.TestData.remoteIssue
import com.waysnpaths.testcore.TestData.user
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteIssueMapperTest {

    @Mock
    private lateinit var remoteUserMapper: RemoteUserMapper

    @InjectMocks
    private lateinit var remoteIssueMapper :RemoteIssueMapper

    @Test
    fun map() {
        whenever(remoteUserMapper.map(TestData.remoteUser)).thenReturn(TestData.user)

        val issue = remoteIssueMapper.map(TestData.remoteIssue)

        assertThat(issue.id, equalTo(remoteIssue.id))
        assertThat(issue.title, equalTo(remoteIssue.title))
        assertThat(issue.body, equalTo(remoteIssue.body))
        assertThat(issue.state, equalTo(remoteIssue.state))
        assertThat(issue.createdAt, equalTo(remoteIssue.createdAt))
        assertThat(issue.user, equalTo(user))
    }
}