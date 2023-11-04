package com.example.nimbletest.login.data.network

import com.example.nimbletest.login.data.network.response.LoginResponse
import retrofit2.Response
import retrofit2.http.POST

interface LoginClient {
    @POST("api/v1/oauth/token")
    suspend fun signIn():Response<LoginResponse>
}