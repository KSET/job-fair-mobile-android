package com.undabot.jobfair.news.list.view.adapters

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.undabot.jobfair.R
import com.undabot.jobfair.news.list.view.models.NewsViewModel
import com.undabot.jobfair.utils.ImageUtils
import kotlinx.android.synthetic.main.news_list_item.view.*

class NewsListAdapter(
        private val newsList: MutableList<NewsViewModel> = mutableListOf(),
        private val newsClickListener: (List<NewsViewModel>, Int) -> Unit
) : RecyclerView.Adapter<NewsListAdapter.NewsListItem>() {

    fun updateItems(payload: List<NewsViewModel>) {
        DiffUtil.calculateDiff(NewsDiffCallback(oldList = newsList, newList = payload))
                .dispatchUpdatesTo(this)
        newsList.clear()
        newsList.addAll(payload)
    }

    override fun getItemViewType(position: Int): Int =
            when (position) {
                0 -> R.layout.news_list_item_header
                else -> R.layout.news_list_item
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsListItem =
            NewsListItem(LayoutInflater.from(parent.context)
                    .inflate(viewType, parent, false))

    override fun getItemCount() = newsList.size

    override fun onBindViewHolder(holder: NewsListItem, position: Int) =
            holder.bind(newsList, position, newsClickListener)

    class NewsListItem(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(allItems: List<NewsViewModel>,
                 position: Int,
                 clickListener: (List<NewsViewModel>, Int) -> Unit) {
            itemView.setOnClickListener { clickListener(allItems, position) }
            itemView.title.text = allItems[position].title
            itemView.datetime.text = allItems[position].datePublished
            ImageUtils.load(
                    context = itemView.context,
                    imageUrl = allItems[position].thumbnailLink,
                    imageView = itemView.image,
                    transformOption = ImageUtils.Transform.CENTER_CROP
            )
        }
    }
}
