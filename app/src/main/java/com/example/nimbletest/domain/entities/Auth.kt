package com.example.nimbletest.domain.entities

import com.example.nimbletest.infrastructure.model.AuthModel

data class Auth(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Long,
    val refreshToken: String,
)

fun AuthModel.toDomain() = Auth(accessToken, tokenType, expiresIn, refreshToken)