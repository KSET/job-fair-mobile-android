package com.undabot.jobfair.companies.list.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.undabot.jobfair.R
import com.undabot.jobfair.companies.list.view.DiffCallback
import com.undabot.jobfair.companies.view.models.CompanyViewModel
import com.undabot.jobfair.utils.GlideApp
import kotlinx.android.synthetic.main.company_item.view.*

class CompaniesAdapter(
    private val itemList: MutableList<CompanyViewModel> = mutableListOf(),
    private val clickListener: (List<CompanyViewModel>, position: Int) -> Unit
) : RecyclerView.Adapter<CompaniesAdapter.CompanyHolder>() {

    fun clear() {
        DiffUtil.calculateDiff(DiffCallback(itemList, emptyList())).dispatchUpdatesTo(this)
        itemList.clear()
    }

    fun updateItems(newItems: List<CompanyViewModel>) {
        DiffUtil.calculateDiff(DiffCallback(itemList, newItems)).dispatchUpdatesTo(this)
        itemList.clear()
        itemList.addAll(newItems)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompanyHolder {
        return CompanyHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.company_item, parent, false))
    }

    override fun getItemCount() = itemList.size

    override fun onBindViewHolder(holder: CompanyHolder, position: Int) {
        holder.bind(itemList[position]) {
            clickListener(itemList, holder.adapterPosition)
        }
    }

    class CompanyHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: CompanyViewModel, clickListener: (CompanyViewModel) -> Unit) {
            itemView.title.text = item.name
            itemView.setOnClickListener { clickListener(item) }
            GlideApp.with(itemView).load(item.logoUrl).into(itemView.logo)
        }
    }
}