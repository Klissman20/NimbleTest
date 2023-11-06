package com.example.nimbletest.infrastructure.datasources

import android.util.Log
import com.example.nimbletest.domain.datasources.NimbleService
import com.example.nimbletest.infrastructure.model.AuthModel
import com.example.nimbletest.infrastructure.model.LogOutBody
import com.example.nimbletest.infrastructure.model.RefreshTokenBody
import com.example.nimbletest.infrastructure.model.SingInBody
import com.example.nimbletest.infrastructure.model.SurveyModel
import com.example.nimbletest.infrastructure.model.UserModel
import com.example.nimbletest.utils.BEARER
import com.example.nimbletest.utils.CLIENT_ID
import com.example.nimbletest.utils.CLIENT_SECRET
import com.example.nimbletest.utils.GRANT_TYPE_P
import com.example.nimbletest.utils.GRANT_TYPE_R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.floor

class NimbleServiceImpl @Inject constructor(private val nimbleClient: NimbleClient) :
    NimbleService {

    override suspend fun signIn(email: String, password: String): AuthModel {

        return withContext(Dispatchers.IO) {

            val singInBody =
                SingInBody(
                    grantType = GRANT_TYPE_P,
                    email = email,
                    password = password,
                    clientId = CLIENT_ID,
                    clientSecret = CLIENT_SECRET
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

    override suspend fun getSurveys(
        pageNumber: String,
        pageSize: String,
        token: String
    ): List<SurveyModel> {
        return withContext(Dispatchers.IO) {

        val authBearer = BEARER + token

            val response =
                nimbleClient.getSurveys(
                    pageNumber = pageNumber,
                    pageSize = pageSize,
                    auth = authBearer
                )
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

    override suspend fun logOut(token: String) {
        withContext(Dispatchers.IO) {
            val logOutBody = LogOutBody(
                token = token,
                clientId = CLIENT_ID,
                clientSecret = CLIENT_SECRET
            )
            nimbleClient.logOut(logOutBody)
        }
    }

    override suspend fun refreshToken(refreshToken: String): AuthModel {
        return withContext(Dispatchers.IO) {
            val refreshTokenBody = RefreshTokenBody (
                grantType = GRANT_TYPE_R,
                refreshToken = refreshToken,
                clientId = CLIENT_ID,
                clientSecret = CLIENT_SECRET
            )
            val response = nimbleClient.refreshToken(refreshTokenBody)
            val authAttribute = response.body()?.data?.attributes
            AuthModel(
                authAttribute?.accessToken ?: "",
                authAttribute?.tokenType ?: "",
                authAttribute?.expiresIn ?: 0,
                authAttribute?.refreshToken ?: ""
            )
        }
    }

    override suspend fun getUserData(token: String): UserModel {
        return withContext(Dispatchers.IO) {

            val authBearer = BEARER + token

            val userResponse = nimbleClient.getUserData(authBearer)
            val userAttributes = userResponse.body()?.data?.attributes
            UserModel(
                email = userAttributes?.email ?: "",
                name = userAttributes?.name ?: "",
                avatarURL = userAttributes?.avatarURL ?: ""
            )
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