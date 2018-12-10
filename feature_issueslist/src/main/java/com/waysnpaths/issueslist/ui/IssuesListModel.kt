package com.waysnpaths.issueslist.ui

import com.waysnpaths.core.domain.model.Issue
import com.waysnpaths.core.mvvm.MvvmEvent

data class IssuesListModel(
    val issues: List<Issue> = listOf(),
    val status: Status = Loaded
)

sealed class Status

object Loading : Status()
object Loaded : Status()
data class Error(val stringId: MvvmEvent<Int>) : Status()