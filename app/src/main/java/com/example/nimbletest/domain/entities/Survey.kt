package com.example.nimbletest.domain.entities

import com.example.nimbletest.infrastructure.model.SurveyModel

data class Survey (
    val title: String,
    val description: String,
    val coverImageURL: String,
)
fun SurveyModel.toDomain() = Survey(title, description,coverImageURL)