package com.undabot.jobfair.companies.list.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.undabot.jobfair.R
import com.undabot.jobfair.companies.details.view.CompanyDetailsContainerScreen
import com.undabot.jobfair.companies.entities.Industry
import com.undabot.jobfair.companies.list.di.CompaniesModule
import com.undabot.jobfair.companies.list.view.adapters.CompaniesAdapter
import com.undabot.jobfair.companies.list.view.adapters.CompaniesFilterArrayAdapter
import com.undabot.jobfair.companies.view.models.CompanyViewModel
import com.undabot.jobfair.companies.view.models.IndustryViewModel
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseFragment
import com.undabot.jobfair.core.view.SpacesItemDecoration
import kotlinx.android.synthetic.main.screen_companies.*
import kotlinx.android.synthetic.main.screen_companies.view.*
import javax.inject.Inject

class CompaniesScreen : BaseFragment(), CompaniesContract.View {

    @Inject
    lateinit var coordinator: CompaniesContract.Coordinator
    private val adapter = CompaniesAdapter(clickListener = { list, position ->
        CompanyDetailsContainerScreen.startWith(activity!!, ArrayList(list), position)
    })

    private var filterAdapter: ArrayAdapter<IndustryViewModel>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.screen_companies, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.info_and_filter, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupFilterSpinner()
        view?.let {
            it.list.layoutManager = GridLayoutManager(activity, 2, RecyclerView.VERTICAL, false)
            it.list.addItemDecoration(SpacesItemDecoration(R.dimen.companies_spacing))
            coordinator.bind(this)
            coordinator.companiesRequested()
            coordinator.industriesRequested()
            it.list.adapter = adapter
        }
    }

    private fun setupFilterSpinner() {
        filterAdapter = CompaniesFilterArrayAdapter(context!!)

        filterSpinner.adapter = filterAdapter
        filterSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                filterAdapter?.getItem(position)?.let { item ->
                    if(item.id == Industry.ALL_ITEMS_FILTER) {
                        coordinator.companiesRequested()
                    } else {
                        coordinator.requestedFilterBy(item)
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
    }

    override fun displayCompanies(items: List<CompanyViewModel>) {
        adapter.updateItems(items)
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
        filterAdapter?.addAll(items)
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