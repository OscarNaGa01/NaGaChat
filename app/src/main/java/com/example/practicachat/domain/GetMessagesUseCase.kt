package com.example.practicachat.domain

import com.example.practicachat.data.network.FirebaseChatService
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService) {

    operator fun invoke() = firebaseChatService.getMessages()

}