package com.waysnpaths.core.remote

import com.waysnpaths.core.CoreContract
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


object RemoteClient {
    val remoteInterface: RemoteInterface by lazy {
        Retrofit.Builder()
            .baseUrl(CoreContract.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(getClient())
            .build().create(RemoteInterface::class.java)
    }

    private fun getClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })

            addInterceptor { chain ->
                val request = chain.request()
                val url = request.url().newBuilder()
                    .addQueryParameter("client_id", CoreContract.clientId)
                    .addQueryParameter("client_secret", CoreContract.clientSecret)
                    .build()
                chain.proceed(request.newBuilder().url(url).build())
            }
        }.build()
    }
}