package com.example.nimbletest.domain.datasources

import com.example.nimbletest.infrastructure.model.AuthModel
import com.example.nimbletest.infrastructure.model.LogOutBody
import com.example.nimbletest.infrastructure.model.RefreshTokenBody
import com.example.nimbletest.infrastructure.model.SurveyModel
import com.example.nimbletest.infrastructure.model.UserModel

interface NimbleService {
    suspend fun signIn(email: String, password: String): AuthModel
    suspend fun getSurveys(pageNumber: String, pageSize: String, token: String): List<SurveyModel>
    suspend fun signUp(email: String, password: String)
    suspend fun logOut(logOutBody: LogOutBody)
    suspend fun refreshToken(refreshTokenBody: RefreshTokenBody) : AuthModel
    suspend fun getUserData(token: String) : UserModel
}