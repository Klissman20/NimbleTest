package com.example.nimbletest.domain.entities

import android.os.Parcelable
import com.example.nimbletest.infrastructure.model.SurveyModel
import com.example.nimbletest.infrastructure.model.SurveyParamType
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class Survey (
    val title: String,
    val description: String,
    val coverImageURL: String,
): Parcelable

fun SurveyModel.toDomain() = Survey(title, description,coverImageURL)