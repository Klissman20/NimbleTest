package com.example.nimbletest.infrastructure

import com.example.nimbletest.infrastructure.network.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api : LoginService){
    suspend fun signIn (email: String, password: String){
        api.signIn(email, password)
    }
}