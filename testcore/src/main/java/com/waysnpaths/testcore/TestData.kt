package com.waysnpaths.testcore

import com.waysnpaths.core.domain.model.Issue
import com.waysnpaths.core.domain.model.User
import com.waysnpaths.core.remote.issue.RemoteIssue
import com.waysnpaths.core.remote.user.RemoteUser
import java.util.*

object TestData {
    val issueId = 234L
    val issueTitle = "title"
    val issueBody = "body"
    val issueState = "open"
    val issueCreatedAt = Date()

    val userLogin = "userLogin"
    val remoteUser = RemoteUser(userLogin)
    val user = User(userLogin)
    val remoteUsers = listOf(remoteUser)
    val users = listOf(user)

    val remoteIssue = RemoteIssue(issueId, issueTitle, issueBody, issueState, issueCreatedAt, remoteUser)
    val remoteIssues = listOf(
        remoteIssue, remoteIssue, remoteIssue
    )
    val issue = Issue(issueId, issueTitle, issueBody, issueState, issueCreatedAt, user)
    val issues = listOf(
        issue, issue,     issue
    )
}