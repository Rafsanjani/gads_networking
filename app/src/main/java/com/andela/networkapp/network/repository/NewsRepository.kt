package com.andela.networkapp.network.repository

import com.andela.networkapp.network.api.NewsApi
import com.andela.networkapp.network.mapper.toNews
import com.andela.networkapp.network.util.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NewsRepository(
    private val newsApi: NewsApi,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun getAllNews() = flow<Resource> {
        val newsResponse = newsApi.getNews()
        val newsList = newsResponse
            .newsList
            .toNews
            .filter { it.content.isNotEmpty() }

        emit(Resource.Success(data = newsList))
    }
        .catch { throwable ->
            emit(Resource.Error(throwable))
        }
        .flowOn(dispatcher)
}