package com.matsu.dai.android_challenge_20240920

import androidx.lifecycle.ViewModel
import com.matsu.dai.android_challenge_20240920.data.repo.ChatWorkRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(val charWorkRepo: ChatWorkRepo): ViewModel() {

    fun fetchMe() {

        // コルーチンを使ってAPIを叩く
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = charWorkRepo.getMe()
                println("User Info: ${response.name}, Avatar: ${response.avatarImageUrl}")
            } catch (e: Exception) {
                e.printStackTrace() // エラーハンドリング
            }
        }
    }
}