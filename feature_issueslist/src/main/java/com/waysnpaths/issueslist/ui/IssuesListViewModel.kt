package com.waysnpaths.issueslist.ui

import com.waysnpaths.core.domain.model.Issue
import com.waysnpaths.core.domain.useCase.issue.GetIssuesUseCase
import com.waysnpaths.core.mvvm.CoreViewModel
import com.waysnpaths.core.mvvm.MvvmEvent
import com.waysnpaths.core.util.extension.plusAssign
import com.waysnpaths.issueslist.R
import timber.log.Timber
import javax.inject.Inject

class IssuesListViewModel @Inject constructor(
    private val getIssuesUseCase: GetIssuesUseCase
) : CoreViewModel<IssuesListModel>() {
    private var currentPage = 1

    fun onInit() {
        model.value = IssuesListModel(status = Loading)
        retrieveIssues(currentPage, ::replaceIssues)
    }

    private fun retrieveIssues(page: Int, callback: (issues: List<Issue>) -> Unit) {
        disposables += getIssuesUseCase.execute(page)
            .subscribe(callback, ::onError)
    }

    private fun replaceIssues(issues: List<Issue>) {
        Timber.d("Loaded ${issues.size} issues, page: $currentPage")
        model.value = IssuesListModel(issues, Loaded)
    }

    private fun onError(throwable: Throwable) {
        model.value = IssuesListModel(status = Error(MvvmEvent(R.string.could_not_load_issues)))
        Timber.e(throwable)
    }

    fun onRefresh() {
        currentPage = 1
        setLoadingState()
        retrieveIssues(currentPage, ::replaceIssues)
    }

    private fun setLoadingState() {
        model.value = model.value?.copy(status = Loading) ?: IssuesListModel(status = Loading)
    }

    fun onNextPage() {
        currentPage++
        setLoadingState()
        retrieveIssues(currentPage, ::addIssues)
    }

    private fun addIssues(issues: List<Issue>) {
        Timber.d("Loaded ${issues.size} issues, page: $currentPage")
        model.value = IssuesListModel(model.value!!.issues + issues, Loaded)
    }
}