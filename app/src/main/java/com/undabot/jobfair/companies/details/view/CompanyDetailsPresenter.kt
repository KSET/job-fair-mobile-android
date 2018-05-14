package com.undabot.jobfair.companies.details.view

import com.undabot.jobfair.companies.details.mappers.CompanyDetailsMapper
import com.undabot.jobfair.companies.entities.DetailedCompany
import com.undabot.jobfair.core.view.AbsPresenter
import javax.inject.Inject

class CompanyDetailsPresenter
@Inject constructor(val mapper: CompanyDetailsMapper) : CompanyDetails.Presenter,
        AbsPresenter<CompanyDetails.View>() {

    override fun displayDetails(details: DetailedCompany) =
        onView { it.displayCompanyDetails(mapper.map(details)) }

    override fun displayError() = onView { it.displayError() }
}