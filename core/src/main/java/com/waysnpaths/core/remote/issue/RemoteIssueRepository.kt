package com.waysnpaths.core.remote.issue

import com.waysnpaths.core.domain.model.Issue
import com.waysnpaths.core.domain.repository.IssueRepository
import com.waysnpaths.core.remote.RemoteInterface
import io.reactivex.Observable

class RemoteIssueRepository(
    private val remoteInterface: RemoteInterface,
    private val remoteIssueMapper: RemoteIssueMapper
) : IssueRepository {
    override fun getIssues(page: Int): Observable<List<Issue>> {
        return remoteInterface.getIssues(page)
            .map { remoteIssueMapper.mapList(it) }
    }
}