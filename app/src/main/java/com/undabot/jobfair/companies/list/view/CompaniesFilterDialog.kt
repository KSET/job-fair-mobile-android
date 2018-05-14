package com.undabot.jobfair.companies.list.view

import android.os.Bundle
import android.support.design.widget.BottomSheetDialogFragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.undabot.jobfair.R
import com.undabot.jobfair.companies.entities.Industry
import com.undabot.jobfair.companies.list.view.adapters.CompaniesFilterAdapter
import com.undabot.jobfair.companies.view.models.IndustryViewModel
import kotlinx.android.synthetic.main.screen_companies_filter.view.*

class CompaniesFilterDialog : BottomSheetDialogFragment() {

    class Builder {
        internal var id: String = ""
            private set

        internal var clickListener: (IndustryViewModel) -> Unit = {}
            private set

        internal var list: List<IndustryViewModel> = emptyList()
            private set

        internal var filterClearedListener: () -> Unit = {}
            private set

        fun industries(filters: List<IndustryViewModel>) = apply { this.list = filters }

        fun selectedFilter(id: String) = apply { this.id = id }

        fun clickListener(itemClickListener: (IndustryViewModel) -> Unit) =
                apply { this.clickListener = itemClickListener }

        fun clearListener(clearListener: () -> Unit) =
                apply { this.filterClearedListener = clearListener }

        fun build() = withFilters(this)
    }

    companion object {

        private const val FILTERS = "LIST_FILTERS"
        private const val ACTIVE_FILTER = "ACTIVE_FILTER"

        fun withFilters(builder: Builder): CompaniesFilterDialog {
            val dialog = CompaniesFilterDialog().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(FILTERS, ArrayList(builder.list))
                    putString(ACTIVE_FILTER, builder.id)
                }
            }
            dialog.setFilterListeners(builder.clickListener, builder.filterClearedListener)
            return dialog
        }
    }

    private lateinit var adapter: CompaniesFilterAdapter
    private lateinit var filterSelected: (IndustryViewModel) -> Unit
    private lateinit var filterClearedListener: () -> Unit

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_companies_filter, container, false)
    }

    override fun onStart() {
        super.onStart()
        view!!.filterList.layoutManager = GridLayoutManager(activity, 2)
        adapter = CompaniesFilterAdapter(arguments?.getParcelableArrayList(FILTERS)!!, {
            filterSelected(it)
            dismiss()
        }, {
            filterClearedListener()
            dismiss()
        }, arguments?.getString(ACTIVE_FILTER, Industry.ALL_ITEMS_FILTER)
                ?: Industry.ALL_ITEMS_FILTER)
        view!!.filterList.adapter = adapter
    }

    fun setFilterListeners(filterListener: (IndustryViewModel) -> Unit, clearListener: () -> Unit) {
        filterSelected = filterListener
        filterClearedListener = clearListener
    }
}