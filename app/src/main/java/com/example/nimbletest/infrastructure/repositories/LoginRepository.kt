package com.example.nimbletest.infrastructure.repositories

import com.example.nimbletest.infrastructure.datasource.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api : LoginService){
    suspend fun signIn (email: String, password: String): Int{
        return api.signIn(email, password)
    }
}