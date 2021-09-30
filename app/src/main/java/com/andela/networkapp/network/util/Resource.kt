package com.andela.networkapp.network.util

import com.andela.networkapp.model.News

sealed class Resource {
    data class Success(val data: List<News>) : Resource()
    data class Error(val error: Throwable) : Resource()
}