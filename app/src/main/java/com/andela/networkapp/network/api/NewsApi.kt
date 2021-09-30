package com.andela.networkapp.network.api

import com.andela.networkapp.network.dto.NewsResponse
import retrofit2.http.GET

interface NewsApi {
    @GET("/news")
    suspend fun getNews(): NewsResponse
}