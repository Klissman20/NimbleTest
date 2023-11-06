package com.example.nimbletest.domain

import com.example.nimbletest.domain.repositories.DataStoreRepository
import javax.inject.Inject

class SetTokenUseCase @Inject constructor(
    private val dataStoreRepository: DataStoreRepository
) {
    suspend operator fun invoke(key: String, value: String) =
        dataStoreRepository.setPreferenceValue(key, value)
}