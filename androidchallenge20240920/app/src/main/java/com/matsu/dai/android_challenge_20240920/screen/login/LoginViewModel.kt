package com.matsu.dai.android_challenge_20240920.screen.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matsu.dai.android_challenge_20240920.data.repo.ChatWorkRepo
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val chatWorkRepo: ChatWorkRepo): ViewModel() {

    private val _tokenText = mutableStateOf("")
    val tokenText: State<String> = _tokenText

    fun onTextChanged(newText: String) {
        _tokenText.value = newText
    }

    private val _errorText = mutableStateOf("")
    val errorText: State<String> = _errorText

    private val _navigationEvent = mutableStateOf<Boolean>(false)
    val navigationEvent: State<Boolean> = _navigationEvent

    private val exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        if (throwable is HttpException) {
            // ログイン時に429エラー以外はトークン保持しておく必要がなさそうなので消す
            // ログイン時なので429も気にせずでいいかも？？
            if (throwable.code() != 429) {
                chatWorkRepo.saveToken("")
                _errorText.value = "ログインに失敗しました。別のトークンをお試しください。"
            } else {
                _errorText.value = "APIの利用回数制限です。時間を置いて再度お試しください。"
            }
        } else {
            chatWorkRepo.saveToken("")
            _errorText.value = "原因不明のエラーが発生しました。時間を置いて再度お試しください。"
        }
    }

    fun fetchMe() {
        viewModelScope.launch(exceptionHandler) {
            withContext(Dispatchers.IO) {
                chatWorkRepo.saveToken(tokenText.value)
                chatWorkRepo.getMe()
           }
            _errorText.value = ""
            _navigationEvent.value = true
        }
    }

    fun resetState() {
        _navigationEvent.value = false
        _errorText.value = ""
        _tokenText.value = ""
    }

}