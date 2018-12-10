package com.waysnpaths.core.domain.repository

import com.waysnpaths.core.domain.model.Issue
import io.reactivex.Observable

interface IssueRepository {
    fun getIssues(page: Int) : Observable<List<Issue>>
}