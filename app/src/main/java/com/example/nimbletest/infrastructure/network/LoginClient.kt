package com.example.nimbletest.infrastructure.network

import com.example.nimbletest.infrastructure.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.POST

interface LoginClient {
    @POST("api/v1/oauth/token")
    suspend fun signIn():Response<LoginResponse>
}