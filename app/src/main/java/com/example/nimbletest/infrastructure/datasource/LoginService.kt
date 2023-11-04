package com.example.nimbletest.infrastructure.datasource

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginService @Inject constructor(private val loginClient: LoginClient){

    suspend fun signIn(email: String, password: String){
        withContext(Dispatchers.IO){
            val response = loginClient.signIn()
        }
    }
}