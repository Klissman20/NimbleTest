package com.example.nimbletest.domain

import com.example.nimbletest.infrastructure.model.LogOutBody
import com.example.nimbletest.infrastructure.repositories.NimbleRepositoryImpl
import javax.inject.Inject

class LogOutUseCase @Inject constructor(
    private val repository: NimbleRepositoryImpl
) {
    suspend operator fun invoke(token: String) = repository.logOut(token)
}