package com.waysnpaths.core.util

import android.content.Context

class StringRetriever(
    private val context: Context
) {
    fun getString(stringId: Int): String {
        return context.getString(stringId)
    }
}