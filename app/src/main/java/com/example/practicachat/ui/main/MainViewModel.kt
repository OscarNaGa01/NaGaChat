package com.example.practicachat.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicachat.domain.SaveUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val saveUserName: SaveUserNameUseCase) : ViewModel() {
    fun saveNickName(nickname: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUserName(nickname)
        }
    }

}