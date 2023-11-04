package com.example.nimbletest.infrastructure.datasource

import com.example.nimbletest.infrastructure.model.SingInBody
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.floor

class LoginService @Inject constructor(private val loginClient: LoginClient) {

    suspend fun signIn(email: String, password: String) : Int {

        return withContext(Dispatchers.IO) {

            val singInBody =
                SingInBody(
                    grantType = "password",
                    email = email,
                    password = password,
                    clientId = "6GbE8dhoz519l2N_F99StqoOs6Tcmm1rXgda4q__rIw",
                    clientSecret = "_ayfIm7BeUAhx2W1OUqi20fwO3uNxfo1QstyKlFCgHw"
                )

            val response = loginClient.signIn(singInBody)
            response.code()
        }
    }

    suspend fun signUp(email: String, password: String) {
        withContext(Dispatchers.IO) {

            val response = loginClient.signUp()
        }
    }

    private fun generateRandom(length: Int): String {
        val characters =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        var result = "";

        for (i in 1..length) {
            result += characters[floor(Math.random() * characters.length).toInt()];
        }
        return result;
    }

}