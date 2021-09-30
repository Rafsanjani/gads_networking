package com.andela.networkapp.model

import java.time.ZonedDateTime

data class News(
    val headline: String,
    val content: String,
    val date: ZonedDateTime,
    val category: String,
    val imageUrl: String,
)