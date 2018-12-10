package com.waysnpaths.core.remote.issue

import com.nhaarman.mockitokotlin2.whenever
import com.waysnpaths.core.remote.RemoteInterface
import com.waysnpaths.testcore.TestData
import io.reactivex.Observable
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteIssueRepositoryTest {

    @Mock
    private lateinit var remoteInterface: RemoteInterface

    @Mock
    private lateinit var remoteIssueMapper: RemoteIssueMapper

    @InjectMocks
    private lateinit var remoteIssueRepository: RemoteIssueRepository

    private val page = 2

    @Test
    fun `get issues success`() {
        whenever(remoteInterface.getIssues(page)).thenReturn(Observable.just(TestData.remoteIssues))
        whenever(remoteIssueMapper.mapList(TestData.remoteIssues)).thenReturn(TestData.issues)

        remoteIssueRepository.getIssues(page)
            .test()
            .assertValue(TestData.issues)
    }

    @Test
    fun `get issues failure`() {
        val exception = Exception()
        whenever(remoteInterface.getIssues(page)).thenReturn(Observable.error(exception))

        remoteIssueRepository.getIssues(page)
            .test()
            .assertError(exception)
    }
}