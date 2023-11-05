package com.example.nimbletest.core.di

import com.example.nimbletest.domain.repositories.DataStoreRepository
import com.example.nimbletest.infrastructure.repositories.DataStoreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DataStoreRepository {
    @Binds
    abstract fun provideDataStoreRepository(dataStoreRepositoryImpl: DataStoreRepositoryImpl): DataStoreRepository
}