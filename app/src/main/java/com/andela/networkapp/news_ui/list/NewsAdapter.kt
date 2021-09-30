package com.andela.networkapp.news_ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andela.networkapp.databinding.RowItemNewsBinding
import com.andela.networkapp.model.News
import com.bumptech.glide.RequestManager
import java.time.format.DateTimeFormatter

class NewsAdapter(
    private val glideRequestManager: RequestManager
) : ListAdapter<News, NewsAdapter.NewsViewHolder>(
    object : DiffUtil.ItemCallback<News>() {
        override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
            return oldItem == newItem
        }
    }
) {

    private val dateFormatter = DateTimeFormatter.ofPattern("MMM, dd")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = RowItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    fun submitNewsList(list: List<News>) {
        submitList(list)
    }

    inner class NewsViewHolder(private val binding: RowItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        //bind a single news item here
        fun bind(news: News) = binding.apply {
            tvHeadline.text = news.headline
            tvCategory.text = news.category
            tvDate.text = dateFormatter.format(news.date)

            glideRequestManager.load(news.imageUrl)
                .into(image)
        }
    }
}