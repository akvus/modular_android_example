package com.waysnpaths.core.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
data class Issue(
    val id: Long,
    val title: String,
    val body: String,
    val state: String,
    val createdAt: Date,
    val user: User
) : Parcelable