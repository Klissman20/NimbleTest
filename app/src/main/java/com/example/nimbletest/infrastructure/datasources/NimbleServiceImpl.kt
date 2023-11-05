package com.example.nimbletest.infrastructure.datasources

import android.util.Log
import com.example.nimbletest.domain.datasources.NimbleService
import com.example.nimbletest.infrastructure.model.AuthModel
import com.example.nimbletest.infrastructure.model.SingInBody
import com.example.nimbletest.infrastructure.model.SurveyModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.floor

class NimbleServiceImpl @Inject constructor(private val nimbleClient: NimbleClient): NimbleService {

    override suspend fun signIn(email: String, password: String): AuthModel {

        return withContext(Dispatchers.IO) {

            val singInBody =
                SingInBody(
                    grantType = "password",
                    email = email,
                    password = password,
                    clientId = "6GbE8dhoz519l2N_F99StqoOs6Tcmm1rXgda4q__rIw",
                    clientSecret = "_ayfIm7BeUAhx2W1OUqi20fwO3uNxfo1QstyKlFCgHw"
                )

            val response = nimbleClient.signIn(singInBody)
            val authAttribute = response.body()?.data?.attributes
            AuthModel(
                authAttribute?.accessToken ?: "",
                authAttribute?.tokenType ?: "",
                authAttribute?.expiresIn ?: 0,
                authAttribute?.refreshToken ?: ""
            )
        }
    }

    override suspend fun getSurveys(pageNumber: String, pageSize: String, token: String): List<SurveyModel> {
        return withContext(Dispatchers.IO) {

            val authBearer = "Bearer $token"

            val response =
                nimbleClient.getSurveys(
                    pageNumber = pageNumber,
                    pageSize = pageSize,
                    auth = authBearer
                )
            Log.d("klis", response.body().toString())
            response.body()?.data?.map {
                SurveyModel(
                    it.attributes.title,
                    it.attributes.description,
                    it.attributes.coverImageURL
                )
            } ?: emptyList()
        }
    }

    override suspend fun signUp(email: String, password: String) {
        withContext(Dispatchers.IO) {

            val response = nimbleClient.signUp()
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