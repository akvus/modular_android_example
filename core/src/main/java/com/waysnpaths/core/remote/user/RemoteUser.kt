package com.waysnpaths.core.remote.user

import com.google.gson.annotations.SerializedName

data class RemoteUser(
    @SerializedName("userLogin")
    val login: String = ""
)