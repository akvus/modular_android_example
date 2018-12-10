package com.waysnpaths.issueslist.ui

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.waysnpaths.core.domain.useCase.issue.GetIssuesUseCase
import com.waysnpaths.testcore.BaseTest
import com.waysnpaths.testcore.TestData
import com.waysnpaths.testcore.TestObserver
import com.waysnpaths.testcore.testObserver
import io.reactivex.Observable
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class IssuesListViewModelTest: BaseTest() {

    @Mock
    private lateinit var getIssuesUseCase: GetIssuesUseCase

    @InjectMocks
    private lateinit var issuesListViewModel : IssuesListViewModel

    private lateinit var testObserver: TestObserver<IssuesListModel>

    private val exception = Exception()

    @Before
    fun setUp() {
        testObserver = issuesListViewModel.getModel().testObserver()
    }

    @Test
    fun `onInit sets initial state to loading`() {
        mockIssuesWithSuccess()

        issuesListViewModel.onInit()

        assertThat(testObserver.observedValues[0]?.status, instanceOf(Loading::class.java))
    }

    private fun mockIssuesWithSuccess() {
        whenever(getIssuesUseCase.execute(any())).thenReturn(Observable.just(TestData.issues))
    }

    @Test
    fun `onInit gets issues`() {
        mockIssuesWithSuccess()

        issuesListViewModel.onInit()

        assertThat(testObserver.observedValues.count(), equalTo(2))
        assertThat(testObserver.observedValues[1]?.issues, equalTo(TestData.issues))
    }

    @Test
    fun `onInit sets state to Loaded once loaded issues`() {
        mockIssuesWithSuccess()

        issuesListViewModel.onInit()

        assertThat(testObserver.observedValues.count(), equalTo(2))
        assertThat(testObserver.observedValues[1]?.status, instanceOf(Loaded::class.java))
    }

    @Test
    fun `onInit retrieve issues with failure - sets error state`() {
        mockIssuesWithFailure()

        issuesListViewModel.onInit()

        assertThat(testObserver.observedValues[1]?.status, instanceOf(Error::class.java))
    }

    private fun mockIssuesWithFailure() {
        whenever(getIssuesUseCase.execute(any())).thenReturn(Observable.error(exception))
    }

    @Test
    fun `onRefresh manages status`() {
        mockIssuesWithSuccess()

        issuesListViewModel.onRefresh()

        assertThat(testObserver.observedValues[0]?.status, instanceOf(Loading::class.java))
        assertThat(testObserver.observedValues[1]?.status, instanceOf(Loaded::class.java))
    }

    @Test
    fun `onRefresh gets issues`() {
        mockIssuesWithSuccess()

        issuesListViewModel.onRefresh()

        assertThat(testObserver.observedValues.count(), equalTo(2))
        assertThat(testObserver.observedValues[1]?.issues, equalTo(TestData.issues))
    }

    @Test
    fun `onRefresh sets error status on failure`() {
        mockIssuesWithFailure()

        issuesListViewModel.onRefresh()

        assertThat(testObserver.observedValues[0]?.status, instanceOf(Loading::class.java))
        assertThat(testObserver.observedValues[1]?.status, instanceOf(Error::class.java))
    }

    @Test
    fun `loadNextPage manages status`() {
        mockIssuesWithSuccess()

        issuesListViewModel.onNextPage()

        assertThat(testObserver.observedValues[0]?.status, instanceOf(Loading::class.java))
        assertThat(testObserver.observedValues[1]?.status, instanceOf(Loaded::class.java))
    }

    @Test
    fun `loadNextPage gets issues`() {
        mockIssuesWithSuccess()

        issuesListViewModel.onNextPage()

        assertThat(testObserver.observedValues[1]?.issues, equalTo(TestData.issues))
    }

    @Test
    fun `loadNextPage sets error status on failure`() {
        mockIssuesWithFailure()

        issuesListViewModel.onNextPage()

        assertThat(testObserver.observedValues[0]?.status, instanceOf(Loading::class.java))
        assertThat(testObserver.observedValues[1]?.status, instanceOf(Error::class.java))
    }
}