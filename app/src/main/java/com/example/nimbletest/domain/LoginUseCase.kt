package com.example.nimbletest.domain

import com.example.nimbletest.infrastructure.repositories.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository : LoginRepository){
    suspend operator fun invoke (email:String, password:String): Int{
        return repository.signIn(email,password)
    }
}