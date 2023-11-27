package com.example.practicachat.data.network.response

import com.example.practicachat.domain.model.MessageModel
import com.example.practicachat.domain.model.UserModel

class MessageResponse (
    private val msg: String? = null,
    private val hour: String? = null,
    private val date: String? = null,
    private val user: UserResponse? = null
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