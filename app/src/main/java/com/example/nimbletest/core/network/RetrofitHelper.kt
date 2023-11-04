package com.example.nimbletest.core.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://survey-api.nimblehq.co/").addConverterFactory(
            GsonConverterFactory.create()
        ).build()
    }
}