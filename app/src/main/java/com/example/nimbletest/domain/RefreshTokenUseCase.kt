package com.example.nimbletest.domain

import com.example.nimbletest.infrastructure.model.LogOutBody
import com.example.nimbletest.infrastructure.model.RefreshTokenBody
import com.example.nimbletest.infrastructure.repositories.NimbleRepositoryImpl
import javax.inject.Inject

class RefreshTokenUseCase @Inject constructor(
    private val repository: NimbleRepositoryImpl
) {
    suspend operator fun invoke(refreshTokenBody: RefreshTokenBody) =
        repository.refreshToken(refreshTokenBody)
}