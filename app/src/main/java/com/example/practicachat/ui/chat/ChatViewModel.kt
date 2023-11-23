package com.example.practicachat.ui.chat

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.practicachat.domain.GetMessagesUseCase
import com.example.practicachat.domain.GetUserNameUseCase
import com.example.practicachat.domain.SendMessageUseCase
import com.example.practicachat.domain.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessagesUseCase: GetMessagesUseCase,
    private val getUserNameUseCase: GetUserNameUseCase
) : ViewModel() {

    var name: String = ""

    init {
        getUserName()
        getMessages()
    }

    private fun getUserName() {
        viewModelScope.launch(Dispatchers.IO) {
            name = getUserNameUseCase()
        }

    }

    private var _messageList = MutableStateFlow<List<MessageModel>>(emptyList())
    val messageList: StateFlow<List<MessageModel>> = _messageList


    private fun getMessages() {
        viewModelScope.launch {
            getMessagesUseCase().collect {
                Log.d("tutorial", "la info es $it")
                _messageList.value = it
            }
        }
    }

    fun sendMessage(msg:String) {
        sendMessageUseCase(msg, name)
    }

}