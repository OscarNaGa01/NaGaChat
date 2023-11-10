package com.example.practicachat.ui.chat

import androidx.lifecycle.ViewModel
import com.example.practicachat.domain.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val sendMessageUseCase: SendMessageUseCase) :
    ViewModel() {

    fun sendMessage() {
        val msg = "Hola"
        sendMessageUseCase(msg)
    }

}