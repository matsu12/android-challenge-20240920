package com.matsu.dai.android_challenge_20240920.screen.list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matsu.dai.android_challenge_20240920.data.repo.ChatWorkRepo
import com.matsu.dai.android_challenge_20240920.data.repo.model.Room
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ChatRoomListViewModel @Inject constructor(private val chatWorkRepo: ChatWorkRepo): ViewModel() {

    private val _rooms = mutableStateOf<List<Room>>(emptyList())
    val rooms: State<List<Room>> = _rooms

    private val _errorText = mutableStateOf("")
    val errorText: State<String> = _errorText

    private val exceptionHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _errorText.value = "ルーム一覧が取得できませんでした。時間を置いてお試しください。"
    }

    fun getRoomList() {
        viewModelScope.launch(exceptionHandler) {
            val result = withContext(Dispatchers.IO) {
                chatWorkRepo.getRooms()
            }
            _rooms.value = result
            _errorText.value = ""
        }
    }

    fun onLogout() {
        chatWorkRepo.saveToken("")

    }
}