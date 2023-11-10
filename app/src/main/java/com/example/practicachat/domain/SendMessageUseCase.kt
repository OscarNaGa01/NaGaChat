package com.example.practicachat.domain

import com.example.practicachat.data.network.FirebaseChatService
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService) {

    operator fun invoke(msg:String){
        firebaseChatService.sendMsgToFirebase(msg)
    }
}