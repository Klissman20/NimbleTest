package com.example.nimbletest.domain.entities

import com.example.nimbletest.infrastructure.model.UserModel

data class User (
    val email: String,
    val name: String,
    val avatarURL: String
)

fun UserModel.toDomain() = User(email, name, avatarURL)