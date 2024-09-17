package com.matsu.dai.android_challenge_20240920.data.repo

import com.matsu.dai.android_challenge_20240920.data.repo.local.LocalData
import com.matsu.dai.android_challenge_20240920.data.repo.model.Message
import com.matsu.dai.android_challenge_20240920.data.repo.model.Room
import com.matsu.dai.android_challenge_20240920.data.repo.model.UserData
import com.matsu.dai.android_challenge_20240920.data.repo.remote.ChatWorkApis
import javax.inject.Inject

class ChatWorkRepo() {

    lateinit var chatWorkApis: ChatWorkApis
    lateinit var localData: LocalData
    @Inject constructor(chatWorkApis: ChatWorkApis, localData: LocalData): this() {
        this.chatWorkApis = chatWorkApis
        this.localData = localData
    }

    suspend fun getMe(): UserData {
        return chatWorkApis.getMe()
    }

    suspend fun getRooms(): List<Room> {
        return chatWorkApis.getRooms()
    }

    suspend fun getMessages(roomId: Int): List<Message>? {
        return chatWorkApis.getMessages(roomId).body()
    }

    suspend fun postMessages(roomId: Int, message: String, selfUnread: Int? = null): Message  {
       return chatWorkApis.postMessages(roomId, message)
    }

    suspend fun getRoom(roomId: Int): Room {
        return chatWorkApis.getRoom(roomId)
    }

    fun saveToken(token: String) {
        localData.saveToken(token)
    }

    fun getToken(): String? {
        return localData.getToken()
    }
}