package com.example.nimbletest.domain

import com.example.nimbletest.domain.entities.Auth
import com.example.nimbletest.infrastructure.repositories.NimbleRepositoryImpl
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository : NimbleRepositoryImpl){
    suspend operator fun invoke (email:String, password:String): Auth {
        return repository.signIn(email,password)
    }
}