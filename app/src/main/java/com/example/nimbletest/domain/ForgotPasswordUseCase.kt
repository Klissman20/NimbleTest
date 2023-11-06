package com.example.nimbletest.domain

import com.example.nimbletest.infrastructure.repositories.NimbleRepositoryImpl
import javax.inject.Inject

class ForgotPasswordUseCase @Inject constructor(private val repository: NimbleRepositoryImpl) {
    suspend operator fun invoke (email: String) = repository.forgotPassword(email)
}