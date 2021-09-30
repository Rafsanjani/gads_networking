package com.andela.networkapp.news_ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.andela.networkapp.model.News
import com.andela.networkapp.network.repository.NewsRepository
import com.andela.networkapp.network.util.Dependencies
import com.andela.networkapp.network.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    //These should ideally be injected via DI
    private val newsService = Dependencies.newsApi

    private val newsRepository = NewsRepository(
        newsApi = newsService,
        dispatcher = Dispatchers.IO
    )

    private val _newsState: MutableLiveData<NewsState> =
        MutableLiveData<NewsState>(NewsState.Loading)

    val newsState: LiveData<NewsState> = _newsState

    init {
        fetchAllNews()
    }

    private fun setNewsState(state: NewsState) {
        _newsState.postValue(state)
    }

    private fun fetchAllNews() = viewModelScope.launch {
        newsRepository
            .getAllNews()
            .collect { resource ->
                when (resource) {
                    is Resource.Error -> setNewsState(NewsState.Error(error = resource.error))
                    is Resource.Success -> {
                        val data = resource.data

                        if (data.isEmpty())
                            setNewsState(NewsState.Empty)
                        else
                            setNewsState(NewsState.Loaded(data = resource.data))
                    }
                }
            }
    }
}

sealed class NewsState {
    data class Loaded(val data: List<News>) : NewsState()
    object Loading : NewsState()
    data class Error(val error: Throwable) : NewsState()
    object Empty : NewsState()
}