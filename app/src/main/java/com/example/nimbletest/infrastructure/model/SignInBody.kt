package com.example.nimbletest.infrastructure.model

import com.google.gson.annotations.SerializedName

data class SingInBody (
    @SerializedName("grant_type") val grantType: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    @SerializedName("client_id") val clientId: String,
    @SerializedName("client_secret") val clientSecret: String
)