package com.example.nimbletest.domain.repositories

import com.example.nimbletest.domain.entities.Auth
import com.example.nimbletest.domain.entities.Survey

interface NimbleRepository {
    suspend fun signIn(email: String, password: String): Auth
    suspend fun getSurveys(pageNumber: String, pageSize: String, auth: String): List<Survey>
}