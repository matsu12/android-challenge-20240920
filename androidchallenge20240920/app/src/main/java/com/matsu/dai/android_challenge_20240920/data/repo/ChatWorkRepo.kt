package com.matsu.dai.android_challenge_20240920.data.repo

import com.matsu.dai.android_challenge_20240920.data.repo.model.UserData
import com.matsu.dai.android_challenge_20240920.data.repo.remote.ChatWorkApis
import javax.inject.Inject

class ChatWorkRepo() {

    lateinit var chatWorkApis: ChatWorkApis
    @Inject constructor(chatWorkApis: ChatWorkApis): this() {
        this.chatWorkApis = chatWorkApis
    }

    suspend fun getMe(): UserData {
        return chatWorkApis.getMe()
    }
}