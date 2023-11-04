package com.example.nimbletest.infrastructure.datasource

import com.example.nimbletest.infrastructure.apiresponse.LoginResponse
import com.example.nimbletest.infrastructure.model.SingInBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface LoginClient {
    @Headers("Content-Type: application/json")
    @POST("api/v1/oauth/token")
    suspend fun signIn(@Body body: SingInBody):Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("api/v1/registrations")
    suspend fun signUp()
}