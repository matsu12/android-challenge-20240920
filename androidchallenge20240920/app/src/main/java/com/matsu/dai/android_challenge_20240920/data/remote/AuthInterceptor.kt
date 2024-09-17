package com.matsu.dai.android_challenge_20240920.data.repo.remote

import com.matsu.dai.android_challenge_20240920.data.repo.local.LocalData
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(val localData: LocalData): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("X-ChatWorkToken", localData.getToken() ?: "")
            .build()
        return chain.proceed(newRequest)
    }
}