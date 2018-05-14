package com.undabot.jobfair.booths.repository.mappers

import BoothsQuery
import com.undabot.jobfair.companies.entities.Company

class BoothCompanyMapper(
        private val logoMapper: BoothCompanyLogoMapper = BoothCompanyLogoMapper()
) {

    fun map(companyResource: BoothsQuery.Company?) = Company(
            id = companyResource?.id().orEmpty(),
            name = companyResource?.name().orEmpty(),
            image = logoMapper.map(companyResource?.logo())
    )
}