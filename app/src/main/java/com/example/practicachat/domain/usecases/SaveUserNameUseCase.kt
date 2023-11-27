package com.example.practicachat.domain.usecases

import com.example.practicachat.domain.DatabaseService
import javax.inject.Inject

class SaveUserNameUseCase @Inject constructor(private val  databaseService: DatabaseService) {
    suspend operator fun invoke (userName:String){
        databaseService.saveUserName(userName)
    }
}