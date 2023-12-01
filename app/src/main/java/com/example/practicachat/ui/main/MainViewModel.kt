package com.example.practicachat.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicachat.domain.GetUserNameUseCase
import com.example.practicachat.domain.SaveUserNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val saveUserName: SaveUserNameUseCase,
    private val getUserNameUseCase: GetUserNameUseCase
) : ViewModel() {

    init {
        verifyUserLogged()
    }

    private var _uiState = MutableStateFlow<MainViewState>(MainViewState.LOADING)
    val uiState: StateFlow<MainViewState> = _uiState

    private fun verifyUserLogged() {
        viewModelScope.launch {
            val name = async { getUserNameUseCase() }.await()
            if (name.isNotEmpty()) {
                _uiState.value = MainViewState.REGISTERED
            }else{
                _uiState.value = MainViewState.UNREGISTERED
            }
        }
    }

    fun saveNickName(nickname: String) {
        viewModelScope.launch(Dispatchers.IO) {
            saveUserName(nickname)
        }
    }

}

sealed class MainViewState {
    object UNREGISTERED : MainViewState()
    object REGISTERED : MainViewState()
    object LOADING : MainViewState()
}