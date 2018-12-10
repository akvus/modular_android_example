package com.waysnpaths.core.domain.useCase.issue

import com.waysnpaths.core.domain.model.Issue
import com.waysnpaths.core.domain.repository.IssueRepository
import com.waysnpaths.core.domain.useCase.UseCase
import io.reactivex.Observable

class GetIssuesUseCase(
    private val issueRepository: IssueRepository
) : UseCase<Int, List<Issue>>() {
    override fun run(params: Int): Observable<List<Issue>> {
        return issueRepository.getIssues(params)
    }
}