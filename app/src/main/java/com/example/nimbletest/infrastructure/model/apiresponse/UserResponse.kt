package com.example.nimbletest.infrastructure.model.apiresponse

import com.google.gson.annotations.SerializedName

data class UserResponse (
    val data: UserData
)

data class UserData (
    val id: String,
    val type: String,
    val attributes: UserAttributes
)

data class UserAttributes (
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("avatar_url") val avatarURL: String
)