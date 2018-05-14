package com.undabot.jobfair.companies.list.view

import com.undabot.jobfair.companies.list.usecases.FilterCompaniesByIndustry
import com.undabot.jobfair.companies.list.usecases.ShowCompanies
import com.undabot.jobfair.companies.list.usecases.ShowIndustriesForCompanies
import com.undabot.jobfair.companies.view.models.CompanyViewModel
import com.undabot.jobfair.companies.view.models.IndustryViewModel
import com.undabot.jobfair.core.view.BaseCoordinator
import com.undabot.jobfair.core.view.BasePresenter

interface CompaniesContract {

    interface Coordinator : BaseCoordinator<View> {
        fun companiesRequested()
        fun industriesRequested()
        fun requestedFilterBy(industryId: IndustryViewModel)
    }

    interface View {
        fun displayCompanies(items: List<CompanyViewModel>)
        fun displayError()
        fun displayLoading()
        fun displayReady()
        fun prepareIndustries(items: List<IndustryViewModel>)
        fun displayEmpty()
        fun displayFilterState(filterState: FilterState)
    }

    interface Presenter : BasePresenter<View>, ShowCompanies.DisplaysCompanies,
            FilterCompaniesByIndustry.DisplaysFiltered, ShowIndustriesForCompanies.DisplaysIndustries

}

sealed class FilterState {
    object Enabled : FilterState()
    object Disabled : FilterState()
}
