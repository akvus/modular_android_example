package com.waysnpaths.core.remote

import com.waysnpaths.core.remote.issue.RemoteIssue
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteInterface {

    @GET("repos/tensorflow/tensorflow/issues")
    fun getIssues(@Query("page") page: Int): Observable<List<RemoteIssue>>
}