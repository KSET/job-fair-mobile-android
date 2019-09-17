package com.undabot.jobfair.companies.repository.mappers

import com.undabot.jobfair.IndustriesQuery
import com.undabot.jobfair.companies.entities.Industry

class IndustryNetworkMapper {

    fun map(industry: IndustriesQuery.Industry) = Industry(
            industry.id().orEmpty(),
            industry.name().orEmpty(),
            industry.companies()?.map { it.id().orEmpty() }.orEmpty()
    )
}