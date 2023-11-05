package com.example.nimbletest.infrastructure.repositories

import com.example.nimbletest.domain.entities.Auth
import com.example.nimbletest.domain.entities.Survey
import com.example.nimbletest.domain.entities.toDomain
import com.example.nimbletest.domain.repositories.NimbleRepository
import com.example.nimbletest.infrastructure.datasources.NimbleServiceImpl
import javax.inject.Inject

class NimbleRepositoryImpl @Inject constructor(private val api : NimbleServiceImpl) : NimbleRepository{
    override suspend fun signIn (email: String, password: String): Auth {
        return api.signIn(email, password).toDomain()
    }

    override suspend fun getSurveys (pageNumber: String, pageSize: String, auth:String) : List<Survey> {
        val response = api.getSurveys(pageNumber,pageSize, auth)
        return response.map { it.toDomain() }
    }
}