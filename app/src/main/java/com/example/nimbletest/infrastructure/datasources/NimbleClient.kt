package com.example.nimbletest.infrastructure.datasources

import com.example.nimbletest.infrastructure.model.LogOutBody
import com.example.nimbletest.infrastructure.model.RefreshTokenBody
import com.example.nimbletest.infrastructure.model.apiresponse.LoginResponse
import com.example.nimbletest.infrastructure.model.apiresponse.SurveyResponse
import com.example.nimbletest.infrastructure.model.SingInBody
import com.example.nimbletest.infrastructure.model.apiresponse.UserResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface NimbleClient {
    @Headers("Content-Type: application/json")
    @POST("api/v1/oauth/token")
    suspend fun signIn(@Body body: SingInBody): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("api/v1/registrations")
    suspend fun signUp()

    @GET("api/v1/surveys")
    suspend fun getSurveys(
        @Query("page[number]") pageNumber: String,
        @Query("page[size]") pageSize: String,
        @Header("Authorization") auth: String
    ): Response<SurveyResponse>

    @POST("api/v1/oauth/revoke")
    suspend fun logOut(@Body body: LogOutBody)

    @POST("api/v1/oauth/token")
    suspend fun refreshToken(@Body body: RefreshTokenBody): Response<LoginResponse>

    @GET("api/v1/me")
    suspend fun getUserData(@Header("Authorization") auth: String): Response<UserResponse>

}