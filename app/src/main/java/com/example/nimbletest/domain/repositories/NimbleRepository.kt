package com.example.nimbletest.domain.repositories

import com.example.nimbletest.domain.entities.Auth
import com.example.nimbletest.domain.entities.Survey
import com.example.nimbletest.infrastructure.model.LogOutBody

interface NimbleRepository {
    suspend fun signIn(email: String, password: String): Auth
    suspend fun getSurveys(pageNumber: String, pageSize: String, auth: String): List<Survey>
    suspend fun logOut(logOutBody: LogOutBody)
}