package com.example.practicachat.domain.model

data class MessageModel (
    val msg: String,
    val hour: String,
    val date: String,
    val user: UserModel
)
data class UserModel(
    val userName: String,
    val admin: Boolean
)