package com.andela.networkapp.news_ui.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.andela.networkapp.databinding.FragmentNewsListBinding
import com.andela.networkapp.model.News
import com.andela.networkapp.news_ui.NewsState
import com.andela.networkapp.news_ui.NewsViewModel
import com.bumptech.glide.Glide

private const val TAG = "NewsListFragment"

class NewsListFragment : Fragment() {
    private val newsViewModel: NewsViewModel by viewModels()

    private var _binding: FragmentNewsListBinding? = null
    private val binding: FragmentNewsListBinding
        get() = _binding!!


    private val newsAdapter by lazy {
        NewsAdapter(
            Glide.with(this)
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpNewsList()

        newsViewModel.newsState.observe(viewLifecycleOwner) { newsState ->
            when (newsState) {
                NewsState.Empty -> renderEmptyState()
                is NewsState.Error -> renderErrorState(newsState.error)
                is NewsState.Loaded -> renderNewsList(list = newsState.data)
                NewsState.Loading -> renderLoadingState()
            }
        }
    }

    private fun setUpNewsList() {
        binding.newsListRecyclerView.adapter = newsAdapter
    }

    private fun renderEmptyState() {
        Log.d(TAG, "renderEmptyState: ")
    }

    private fun renderErrorState(error: Throwable) {
        binding.progress.visibility = View.INVISIBLE
        binding.layoutError.visibility = View.VISIBLE

        Log.d(TAG, "renderErrorState: $error")
    }

    private fun renderNewsList(list: List<News>) {
        binding.progress.visibility = View.INVISIBLE
        newsAdapter.submitNewsList(list)
    }

    private fun renderLoadingState() {
        binding.progress.visibility = View.INVISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}