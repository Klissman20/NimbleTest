package com.example.nimbletest.login.domain

import com.example.nimbletest.login.data.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository : LoginRepository){
    suspend operator fun invoke (email:String, password:String){
        repository.signIn(email,password)
    }
}