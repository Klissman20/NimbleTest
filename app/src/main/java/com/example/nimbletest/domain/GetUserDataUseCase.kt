package com.example.nimbletest.domain

import com.example.nimbletest.domain.entities.User
import com.example.nimbletest.infrastructure.repositories.NimbleRepositoryImpl
import javax.inject.Inject

class GetUserDataUseCase @Inject constructor(private val repository: NimbleRepositoryImpl) {
    suspend operator fun invoke (token:String): User {
        return repository.getUserData(token)
    }
}