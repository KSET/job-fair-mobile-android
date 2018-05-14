package com.undabot.jobfair.companies.list.usecases

import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.companies.entities.Industry
import com.undabot.jobfair.companies.repository.CompaniesRepository
import com.undabot.jobfair.core.schedulers.MainScheduler
import javax.inject.Inject

class FilterCompaniesByIndustry
@Inject
constructor(private val repository: CompaniesRepository,
            private val mainScheduler: MainScheduler) {

    fun filter(industry: Industry, presenter: DisplaysFiltered) {
        presenter.displayFilterState(industry.id != Industry.ALL_ITEMS_FILTER)
        repository.fetchCompanies()
                .observeOn(mainScheduler.get())
                .subscribe(
                        { handleSuccess(it, presenter, industry) },
                        { presenter.displayError() }
                )
    }

    private fun handleSuccess(list: List<Company>,
                              presenter: DisplaysFiltered,
                              industry: Industry) {
        when (list.isEmpty()) {
            true -> presenter.displayEmpty()
            false -> presenter.displayFilteredCompanies(
                    list.filter { industry.companyIds.contains(it.id) })
        }
    }

    interface DisplaysFiltered {
        fun displayFilterState(isEnabled: Boolean)
        fun displayFilteredCompanies(companies: List<Company>)
        fun displayEmpty()
        fun displayError()
    }
}