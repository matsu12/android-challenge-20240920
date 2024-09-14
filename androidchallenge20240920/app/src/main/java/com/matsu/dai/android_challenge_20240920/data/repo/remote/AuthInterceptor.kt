package com.matsu.dai.android_challenge_20240920.data.repo.remote

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(): Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest: Request = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("X-ChatWorkToken", "3639c997170e1ac59f8379ac2bd60ff9") // トークンを共通で追加
            .build()
        return chain.proceed(newRequest)
    }
}