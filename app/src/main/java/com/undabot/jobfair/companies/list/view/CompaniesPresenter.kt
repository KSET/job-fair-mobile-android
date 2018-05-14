package com.undabot.jobfair.companies.list.view

import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.companies.entities.Industry
import com.undabot.jobfair.companies.view.mappers.CompanyViewModelMapper
import com.undabot.jobfair.companies.view.mappers.IndustryItemMapper
import com.undabot.jobfair.core.view.AbsPresenter
import javax.inject.Inject

class CompaniesPresenter
@Inject
constructor(
        private val companyViewModelMapper: CompanyViewModelMapper,
        private val industryItemMapper: IndustryItemMapper
) : AbsPresenter<CompaniesContract.View>(), CompaniesContract.Presenter {

    override fun displayFilterState(isEnabled: Boolean) {
        onView {
            it.displayFilterState(when (isEnabled) {
                true -> FilterState.Enabled
                false -> FilterState.Disabled
            })
        }
    }

    override fun displayEmpty() {
        onView {
            it.displayEmpty()
            it.displayReady()
        }
    }

    override fun displayLoading() {
        onView {
            it.displayLoading()
        }
    }

    override fun displayCompanies(items: List<Company>) {
        onView {
            it.displayCompanies(items.map { companyViewModelMapper.map(it) })
            it.displayReady()
        }
    }

    override fun displayError() {
        onView {
            it.displayError()
            it.displayReady()
        }
    }

    override fun displayIndustries(list: List<Industry>) {
        onView {
            it.prepareIndustries(list.map { industryItemMapper.map(it) })
        }
    }

    override fun displayFilteredCompanies(companies: List<Company>) {
        onView {
            it.displayCompanies(companies.map { companyViewModelMapper.map(it) })
            it.displayReady()
        }
    }
}
