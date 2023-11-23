package com.example.practicachat.domain

import com.example.practicachat.data.network.FirebaseChatService
import com.example.practicachat.data.network.dto.MessageDto
import com.example.practicachat.data.network.dto.UserDto
import java.util.Calendar
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService) {

    operator fun invoke(msg:String, nickname:String){
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val min = calendar.get(Calendar.MINUTE)
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val userDto = UserDto(nickname, false)
        val messageDto = MessageDto(
            msg,
            "$hour:$min",
            "$day/$month/$year",
            userDto
        )
        firebaseChatService.sendMsgToFirebase(messageDto)
    }
}