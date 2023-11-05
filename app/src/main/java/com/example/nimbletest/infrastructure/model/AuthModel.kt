package com.example.nimbletest.infrastructure.model

data class AuthModel (
   val accessToken: String,
   val tokenType: String,
   val expiresIn: Long,
   val refreshToken: String,
)