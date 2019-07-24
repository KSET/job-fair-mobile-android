package com.undabot.jobfair.events.repository.mappers

import com.undabot.jobfair.PresentationsQuery
import com.undabot.jobfair.companies.entities.Company

class PresentationsCompanyMapper(
    private val logoMapper: PresentationsCompanyLogoMapper = PresentationsCompanyLogoMapper()
) {

    fun map(companyResource: PresentationsQuery.Company?) = Company(
            id = companyResource?.id().orEmpty(),
            name = companyResource?.name().orEmpty(),
            image = logoMapper.map(companyResource?.logo())
    )
}