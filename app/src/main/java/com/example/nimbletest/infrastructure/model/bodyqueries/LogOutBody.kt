package com.example.nimbletest.infrastructure.model.bodyqueries

import com.google.gson.annotations.SerializedName

data class LogOutBody (
    @SerializedName("token") val token: String,
    @SerializedName("client_id") val clientId: String,
    @SerializedName("client_secret") val clientSecret: String
)