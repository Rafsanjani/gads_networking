package com.andela.networkapp.network.dto

import com.google.gson.annotations.SerializedName


data class NewsResponse(
    @SerializedName("status")
    val status: String,

    @SerializedName("message")
    val message: String,

    @SerializedName(value = "news")
    val newsList: List<NewsDto>
)

data class NewsDto(
    @SerializedName(value = "headline")
    val headline: String?,

    @SerializedName(value = "content")
    val content: String?,

    @SerializedName(value = "date")
    val date: String?,

    @SerializedName(value = "category")
    val category: String?,

    @SerializedName(value = "imageUrl")
    val imageUrl: String?
)