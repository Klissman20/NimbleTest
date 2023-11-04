package com.example.nimbletest.login.data

import com.example.nimbletest.login.data.network.LoginService
import javax.inject.Inject

class LoginRepository @Inject constructor(private val api : LoginService){
    suspend fun signIn (email: String, password: String){
        api.signIn(email, password)
    }
}