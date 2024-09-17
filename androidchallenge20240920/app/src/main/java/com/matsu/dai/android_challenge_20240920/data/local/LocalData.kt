package com.matsu.dai.android_challenge_20240920.data.repo.local

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

class LocalData @Inject constructor(val context: Context) {

    fun saveToken(token: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(KEY_TOKEN, token)
        editor.apply()
    }

    fun getToken(): String? {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

    fun removeToken() {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.remove(KEY_TOKEN).apply()
    }

    companion object {
        const val preferenceName: String = "chat_work_preference"
        const val KEY_TOKEN = "CHATWORK_API_TOKEN"
    }
}