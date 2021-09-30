package com.andela.networkapp.network.mapper

import com.andela.networkapp.model.News
import com.andela.networkapp.network.dto.NewsDto
import java.time.ZonedDateTime

val NewsDto.toNews
    get() =
        News(
            headline = headline ?: "",
            content = content ?: "",
            date = ZonedDateTime.parse(date),
            category = category ?: "",
            imageUrl = imageUrl ?: ""
        )


val List<NewsDto>.toNews get() = map { it.toNews }