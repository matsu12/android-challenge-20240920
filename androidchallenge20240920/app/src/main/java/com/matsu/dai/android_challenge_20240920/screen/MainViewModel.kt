package com.matsu.dai.android_challenge_20240920.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.matsu.dai.android_challenge_20240920.data.repo.ChatWorkRepo
import javax.inject.Inject

class MainViewModel @Inject constructor(private val chatWorkRepo: ChatWorkRepo): ViewModel() {

    // すでにログインずみかのチェック用
    private val _localTokenText = mutableStateOf("")
    val localTokenText: State<String> = _localTokenText

    init {
        _localTokenText.value = chatWorkRepo.getToken() ?: ""
    }
}