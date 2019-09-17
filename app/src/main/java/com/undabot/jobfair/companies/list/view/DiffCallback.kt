package com.undabot.jobfair.companies.list.view

import androidx.recyclerview.widget.DiffUtil
import com.undabot.jobfair.companies.view.models.CompanyViewModel

class DiffCallback(
    private val oldList: List<CompanyViewModel>,
    private val newList: List<CompanyViewModel>
) : DiffUtil.Callback() {

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldList[oldItemPosition] == newList[newItemPosition]


}