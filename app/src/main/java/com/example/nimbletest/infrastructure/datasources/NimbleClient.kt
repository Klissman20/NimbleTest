package com.example.nimbletest.infrastructure.datasources

import com.example.nimbletest.infrastructure.apiresponse.LoginResponse
import com.example.nimbletest.infrastructure.apiresponse.SurveyResponse
import com.example.nimbletest.infrastructure.model.SingInBody
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
}