package com.waysnpaths.core.remote.issue

import com.waysnpaths.core.domain.Mapper
import com.waysnpaths.core.domain.model.Issue
import com.waysnpaths.core.remote.user.RemoteUserMapper

class RemoteIssueMapper(
    private val remoteUserMapper: RemoteUserMapper
): Mapper<RemoteIssue, Issue> {
    override fun map(from: RemoteIssue): Issue {
        return Issue(from.id, from.title, from.body, from.state, from.createdAt, remoteUserMapper.map(from.user))
    }
}