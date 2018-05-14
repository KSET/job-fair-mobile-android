package com.undabot.jobfair.news.list.view.adapters

import android.support.v7.util.DiffUtil
import com.undabot.jobfair.news.list.view.models.NewsViewModel

class NewsDiffCallback(private val oldList: MutableList<NewsViewModel>,
                       private val newList: List<NewsViewModel>) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
            oldList[oldItemPosition] == newList[newItemPosition]


}