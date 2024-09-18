package com.matsu.dai.android_challenge_20240920.screen.librarylist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matsu.dai.android_challenge_20240920.data.local.LibraryLicense
import com.matsu.dai.android_challenge_20240920.data.repo.ChatWorkRepo
import com.matsu.dai.android_challenge_20240920.data.repo.model.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LibraryLicenseListViewModel @Inject constructor(private val chatWorkRepo: ChatWorkRepo): ViewModel() {

    private val _libraryList = mutableStateOf<List<LibraryLicense>>(emptyList())
    val libraryList: State<List<LibraryLicense>> = _libraryList

    fun getLibraryList() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                chatWorkRepo.getLibsList()
            }
            _libraryList.value = result
        }
    }
}