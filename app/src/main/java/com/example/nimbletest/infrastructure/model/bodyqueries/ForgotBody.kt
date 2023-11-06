package com.example.nimbletest.infrastructure.model.bodyqueries

import com.google.gson.annotations.SerializedName

data class ForgotBody (
    val user: User,
    @SerializedName("client_id") val clientID: String,
    @SerializedName("client_secret") val clientSecret: String
)

data class User (
    val email: String
)