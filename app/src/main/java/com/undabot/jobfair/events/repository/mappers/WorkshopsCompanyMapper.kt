package com.undabot.jobfair.events.repository.mappers

import com.undabot.jobfair.WorkshopsQuery
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