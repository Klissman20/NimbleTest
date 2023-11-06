package com.example.nimbletest.domain

import com.example.nimbletest.domain.repositories.DataStoreRepository
import javax.inject.Inject

class ClearPreferencesUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(key: String) = dataStoreRepository.clearPreferences(key)
}