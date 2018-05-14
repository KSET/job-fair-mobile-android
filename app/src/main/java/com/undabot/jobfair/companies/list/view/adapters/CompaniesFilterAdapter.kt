package com.undabot.jobfair.companies.list.view.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.undabot.jobfair.R
import com.undabot.jobfair.companies.entities.Industry
import com.undabot.jobfair.companies.view.models.IndustryViewModel
import kotlinx.android.synthetic.main.filter_item.view.*

class CompaniesFilterAdapter(
        private val items: List<IndustryViewModel>,
        private val onItemSelected: (IndustryViewModel) -> Unit,
        private val onFilterCleared: () -> Unit,
        private var selectedIndustryId: String
) :
        RecyclerView.Adapter<CompaniesFilterAdapter.FilterHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilterHolder {
        return FilterHolder(LayoutInflater.from(parent.context)
                .inflate(viewType, parent, false))
    }

    override fun getItemViewType(position: Int) = when (true) {
        items[position].id == selectedIndustryId -> R.layout.filter_item_selected
        else -> R.layout.filter_item
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: FilterHolder, position: Int) {
        holder.bind(items[position], {
            if (it.id == Industry.ALL_ITEMS_FILTER)
                onFilterCleared()
            else onItemSelected(it)
        })
    }

    class FilterHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(viewModel: IndustryViewModel, onItemSelected: (IndustryViewModel) -> Unit) {
            itemView.filterItem.text = viewModel.name
            itemView.filterItem.setOnClickListener { onItemSelected(viewModel) }
        }
    }
}
