package com.example.nimbletest.domain

import com.example.nimbletest.domain.entities.Survey
import com.example.nimbletest.infrastructure.repositories.NimbleRepositoryImpl
import javax.inject.Inject

class GetSurveysUseCase @Inject constructor(private val repository: NimbleRepositoryImpl) {
    suspend operator fun invoke (pageNumber: String, pageSize:String, auth: String): List<Survey>{
        return repository.getSurveys(pageNumber, pageSize, auth)
    }
}