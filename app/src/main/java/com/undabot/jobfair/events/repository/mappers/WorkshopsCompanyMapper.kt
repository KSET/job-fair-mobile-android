package com.undabot.jobfair.events.repository.mappers

import WorkshopsQuery
import com.undabot.jobfair.companies.entities.Company

class WorkshopsCompanyMapper(
    private val logoMapper: WorkshopsCompanyLogoMapper = WorkshopsCompanyLogoMapper()
) {

    fun map(companyResource: WorkshopsQuery.Company?) = Company(
            id = companyResource?.id().orEmpty(),
            name = companyResource?.name().orEmpty(),
            image = logoMapper.map(companyResource?.logo())
    )
}