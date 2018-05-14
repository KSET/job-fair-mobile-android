package com.undabot.jobfair.companies.repository.mappers

import CompaniesQuery
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.core.entities.Image

class CompanyNetworkMapper {

    fun map(company: CompaniesQuery.Company) = Company(
            id = company.id().orEmpty(),
            image = Image(small = company.logo()?.small()?.url().orEmpty()),
            name = company.name().orEmpty())
}