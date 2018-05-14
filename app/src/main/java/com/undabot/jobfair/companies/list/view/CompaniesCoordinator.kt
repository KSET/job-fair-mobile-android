package com.undabot.jobfair.companies.list.view

import com.undabot.jobfair.companies.entities.Industry
import com.undabot.jobfair.companies.list.usecases.FilterCompaniesByIndustry
import com.undabot.jobfair.companies.list.usecases.ShowCompanies
import com.undabot.jobfair.companies.list.usecases.ShowIndustriesForCompanies
import com.undabot.jobfair.companies.view.models.IndustryViewModel
import com.undabot.jobfair.core.view.AbsCoordinator
import javax.inject.Inject

class CompaniesCoordinator @Inject constructor(
        presenter: CompaniesContract.Presenter,
        private val industriesForCompanies: ShowIndustriesForCompanies,
        private val companies: ShowCompanies,
        private val filterCompaniesByIndustry: FilterCompaniesByIndustry
) :
        AbsCoordinator<CompaniesContract.View, CompaniesContract.Presenter>(presenter),
        CompaniesContract.Coordinator {

    override fun industriesRequested() = industriesForCompanies.fetch(presenter)

    override fun requestedFilterBy(industryId: IndustryViewModel) =
            filterCompaniesByIndustry.filter(
                    Industry(industryId.id, industryId.name, industryId.companies),
                    presenter)

    override fun companiesRequested() = companies.fetch(presenter)
}