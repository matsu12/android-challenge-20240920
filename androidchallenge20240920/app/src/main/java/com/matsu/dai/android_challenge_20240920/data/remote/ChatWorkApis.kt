package com.matsu.dai.android_challenge_20240920.data.repo.remote

import com.matsu.dai.android_challenge_20240920.data.repo.model.Message
import com.matsu.dai.android_challenge_20240920.data.repo.model.Room
import com.matsu.dai.android_challenge_20240920.data.repo.model.UserData
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ChatWorkApis {
    @GET("me")
    suspend fun getMe(): UserData

    @GET("rooms")
    suspend fun getRooms(): List<Room>

    @GET("rooms/{room_id}")
    suspend fun getRoom(@Path("room_id") roomId: Int): Room

    @GET("rooms/{room_id}/messages")
    suspend fun getMessages(@Path("room_id") roomId: Int, @Query("force") force: Int = 1): Response<List<Message>>

    @FormUrlEncoded
    @POST("rooms/{room_id}/messages")
    suspend fun postMessages(@Path("room_id") roomId: Int, @Field("body") message: String, @Field("self_unread") selfUnRead: Int? = null): Message
}