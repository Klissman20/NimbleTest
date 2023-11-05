package com.example.nimbletest.domain.repositories

interface DataStoreRepository {
    suspend fun setTokenValue(key: String, value: String)
    suspend fun getTokenValue(key: String): String?
}