package com.waysnpaths.core.domain.useCase.issue

import com.nhaarman.mockitokotlin2.whenever
import com.waysnpaths.core.domain.repository.IssueRepository
import com.waysnpaths.testcore.BaseTest
import com.waysnpaths.testcore.TestData
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetIssuesUseCaseTest : BaseTest() {

    @Mock
    private lateinit var issueRepository: IssueRepository

    private lateinit var getIssuesUseCase: GetIssuesUseCase

    private val page = 1

    @Before
    fun setUp() {
        getIssuesUseCase = GetIssuesUseCase(issueRepository)
    }

    @Test
    fun `run delivers list of issues`() {
        whenever(issueRepository.getIssues(page)).thenReturn(Observable.just(TestData.issues))
        getIssuesUseCase.execute(page)
            .test()
            .assertValue(TestData.issues)
    }

    @Test
    fun `run fails`() {
        val exception = Exception()
        whenever(issueRepository.getIssues(page)).thenReturn(Observable.error(exception))
        getIssuesUseCase.execute(page)
            .test()
            .assertError(exception)
    }
}