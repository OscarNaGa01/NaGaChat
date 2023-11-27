package com.example.practicachat.domain.usecases

import com.example.practicachat.data.network.api.FirebaseChatService
import javax.inject.Inject

class GetMessagesUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService) {

    operator fun invoke() = firebaseChatService.getMessages()

}