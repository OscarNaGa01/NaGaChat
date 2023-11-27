package com.example.practicachat.domain.usecases

import com.example.practicachat.data.network.api.FirebaseChatService
import com.example.practicachat.data.network.dto.MessageDto
import com.example.practicachat.data.network.dto.UserDto
import java.util.Calendar
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(private val firebaseChatService: FirebaseChatService) {

    operator fun invoke(msg:String, nickname:String){
        val calendar = Calendar.getInstance()
        val hour = calendar[Calendar.HOUR_OF_DAY]
        val min = calendar[Calendar.MINUTE]
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH]
        val day = calendar[Calendar.DAY_OF_MONTH]
        //If the API (in this case, Firebase) provides a way to access as
        //operators (like the [] operator in Kotlin), we should use it instead of calling it
        //as a function (like get(Calendar.DAY_OF_MONTH))
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