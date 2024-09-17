package com.matsu.dai.android_challenge_20240920

import android.text.TextUtils
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matsu.dai.android_challenge_20240920.data.repo.ChatWorkRepo
import com.matsu.dai.android_challenge_20240920.data.repo.model.Message
import com.matsu.dai.android_challenge_20240920.data.repo.model.Room
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import javax.inject.Inject

class RoomDetailViewModel @Inject constructor(private val chatWorkRepo: ChatWorkRepo): ViewModel() {

    private var _roomId = 0

    private val _messages = mutableStateOf<List<Message>>(emptyList())
    val messages: State<List<Message>> = _messages

    private val _roomName = mutableStateOf<String>("")
    val roomName: State<String> = _roomName

    // 入力値のState
    private val _messageText = mutableStateOf("")
    val messageText: State<String> = _messageText

    fun onTextChanged(newText: String) {
        _messageText.value = newText
    }

    // エラーメッセージ用のState
    private val _errorText = mutableStateOf("")
    val errorText: State<String> = _errorText

    private val exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _errorText.value = "メッセージを送信できませんでした。時間を置いてお試しください"
    }

    fun fetchRoomData(roomId: Int) {
        _roomId = roomId
        viewModelScope.launch(exceptionHandler) {
            val messages = withContext(Dispatchers.IO) {
                chatWorkRepo.getMessages(roomId)
            }
            val roomDesc = withContext(Dispatchers.IO) {
                chatWorkRepo.getRoom(roomId)
            }
            _messages.value = messages ?: mutableListOf()
            _roomName.value = roomDesc.name ?: ""
        }
    }

    fun sendMessage() {
        if (TextUtils.isEmpty(_messageText.value)) {
            _errorText.value = "メッセージを入力してください"
            return
        }
        viewModelScope.launch(exceptionHandler) {
            val result = withContext(Dispatchers.IO) {
                chatWorkRepo.postMessages(_roomId, _messageText.value)
                _messageText.value = ""
            }
            delay(1000)
            fetchRoomData(_roomId)
        }
    }
}