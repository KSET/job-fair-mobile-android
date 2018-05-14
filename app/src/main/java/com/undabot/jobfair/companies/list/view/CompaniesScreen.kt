package com.undabot.jobfair.companies.list.view

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import com.undabot.jobfair.R
import com.undabot.jobfair.companies.details.view.CompanyDetailsContainerScreen
import com.undabot.jobfair.companies.entities.Industry
import com.undabot.jobfair.companies.list.di.CompaniesModule
import com.undabot.jobfair.companies.list.view.adapters.CompaniesAdapter
import com.undabot.jobfair.companies.view.models.CompanyViewModel
import com.undabot.jobfair.companies.view.models.IndustryViewModel
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseFragment
import kotlinx.android.synthetic.main.screen_companies.view.*
import javax.inject.Inject

class CompaniesScreen : BaseFragment(), CompaniesContract.View {

    @Inject
    lateinit var coordinator: CompaniesContract.Coordinator
    private val adapter = CompaniesAdapter(clickListener = { list, position ->
        CompanyDetailsContainerScreen.startWith(activity!!, ArrayList(list), position)
    })

    private var bottomSheetDialog: CompaniesFilterDialog.Builder? = null
    private var selectedFilter = Industry.ALL_ITEMS_FILTER
    var menu: Menu? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_companies, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.info_and_filter, menu)
        this.menu = menu
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onMenuItemClick(it: MenuItem): Boolean {
        if (it.itemId == R.id.action_filter) {
            openFilters()
        }
        return true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        view?.let {
            it.list.layoutManager = GridLayoutManager(activity, 2)
            coordinator.bind(this)
            coordinator.companiesRequested()
            coordinator.industriesRequested()
            it.list.adapter = adapter
        }
    }

    private fun openFilters() {
        bottomSheetDialog?.let {
            it.clickListener {
                selectedFilter = it.id
                coordinator.requestedFilterBy(it)
            }.selectedFilter(selectedFilter).clearListener {
                selectedFilter = Industry.ALL_ITEMS_FILTER
                coordinator.companiesRequested()
                displayFilterState(FilterState.Disabled)
            }.build().show(activity!!.supportFragmentManager, "")
        }
    }

    private fun changeFilterIcon(filterState: FilterState, menuItem: MenuItem?) {
        when (filterState) {
            FilterState.Disabled -> menuItem?.setIcon(R.drawable.filter_empty)
            FilterState.Enabled -> menuItem?.setIcon(R.drawable.filter_selected)
        }
    }

    override fun displayCompanies(items: List<CompanyViewModel>) {
        adapter.updateItems(items)
    }

    override fun displayFilterState(filterState: FilterState) {
        changeFilterIcon(filterState, menu?.findItem(R.id.action_filter))
    }

    override fun displayError() {
        showGeneralErrorMessage()
    }

    override fun displayLoading() {
        view!!.loading.visibility = View.VISIBLE
    }

    override fun displayReady() {
        view!!.loading.visibility = View.GONE
    }

    override fun prepareIndustries(items: List<IndustryViewModel>) {
        bottomSheetDialog = CompaniesFilterDialog.Builder().industries(items)
    }

    override fun displayEmpty() {
        adapter.clear()
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(CompaniesModule()).inject(this)
    }

    override fun onDestroyView() {
        coordinator.unbind(this)
        super.onDestroyView()
    }
}