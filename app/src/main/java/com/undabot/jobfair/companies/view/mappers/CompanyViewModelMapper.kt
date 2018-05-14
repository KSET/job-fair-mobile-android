package com.undabot.jobfair.companies.view.mappers

import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.companies.view.models.CompanyViewModel
import javax.inject.Inject

class CompanyViewModelMapper @Inject constructor() {

    fun map(company: Company) =
            CompanyViewModel(company.id, company.image.small, company.name)
}