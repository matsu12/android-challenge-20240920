package com.matsu.dai.android_challenge_20240920

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matsu.dai.android_challenge_20240920.data.repo.ChatWorkRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @Inject constructor(private val chatWorkRepo: ChatWorkRepo): ViewModel() {

    // すでにログインずみかのチェック用
    private val _localTokenText = mutableStateOf("")
    val localTokenText: State<String> = _localTokenText

    init {
        _localTokenText.value = chatWorkRepo.getToken() ?: ""
    }
}