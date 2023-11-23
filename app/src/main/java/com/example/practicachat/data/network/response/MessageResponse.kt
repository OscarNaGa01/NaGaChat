package com.example.practicachat.data.network.response

import com.example.practicachat.domain.model.MessageModel
import com.example.practicachat.domain.model.UserModel

class MessageResponse (
    val msg: String? = null,
    val hour: String? = null,
    val date: String? = null,
    val user: UserResponse? = null
) {
    fun toDomain(): MessageModel {
        return MessageModel(
            msg.orEmpty(),
            hour ?: "no date",
            date.orEmpty(),
            user = UserModel(userName = user?.userName ?: "Guess", admin = user?.admin ?: false)
        )
    }
}

data class UserResponse(
    val userName: String? = null,
    val admin: Boolean? = null
)