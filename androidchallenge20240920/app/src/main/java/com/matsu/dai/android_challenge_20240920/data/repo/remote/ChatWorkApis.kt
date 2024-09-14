package com.matsu.dai.android_challenge_20240920.data.repo.remote

import com.matsu.dai.android_challenge_20240920.data.repo.model.UserData
import retrofit2.http.GET

interface ChatWorkApis {
    @GET("me")
    suspend fun getMe(): UserData
}