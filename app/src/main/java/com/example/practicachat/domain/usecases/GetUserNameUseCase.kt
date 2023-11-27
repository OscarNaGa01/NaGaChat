package com.example.practicachat.domain.usecases

import com.example.practicachat.domain.DatabaseService
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetUserNameUseCase @Inject constructor(private val databaseService: DatabaseService) {

    suspend operator fun invoke(): String = databaseService.getUserName().first()

}