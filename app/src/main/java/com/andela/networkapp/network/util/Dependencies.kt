package com.andela.networkapp.network.util

import com.andela.networkapp.network.api.NewsApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Dependencies {
    private const val baseUrl = "https://ardent-gate-173721.firebaseapp.com/"

    val newsApi: NewsApi = Retrofit
        .Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)
}