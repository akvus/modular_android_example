package com.waysnpaths.core.remote.issue

import com.google.gson.annotations.SerializedName
import com.waysnpaths.core.remote.user.RemoteUser
import java.util.*

data class RemoteIssue(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String,
    @SerializedName("body")
    val body: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("created_at")
    val createdAt: Date,
    val user: RemoteUser
)