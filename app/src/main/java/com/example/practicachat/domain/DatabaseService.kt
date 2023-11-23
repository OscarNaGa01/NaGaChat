package com.example.practicachat.domain

import kotlinx.coroutines.flow.Flow


interface DatabaseService {
    suspend fun saveUserName(nickname: String)
    fun getUserName() : Flow<String>
}