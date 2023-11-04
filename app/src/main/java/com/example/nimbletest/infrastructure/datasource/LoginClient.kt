package com.example.nimbletest.infrastructure.datasource

import com.example.nimbletest.infrastructure.apiresponse.LoginResponse
import retrofit2.Response
import retrofit2.http.POST

interface LoginClient {
    @POST("api/v1/oauth/token")
    suspend fun signIn():Response<LoginResponse>

    @POST("api/v1/registrations")
    suspend fun signUp()
}