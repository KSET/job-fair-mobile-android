package com.undabot.jobfair.events.repository.mappers

import com.undabot.jobfair.PresentationsQuery
import com.undabot.jobfair.core.entities.Image

class PresentationsCompanyLogoMapper {

    fun map(companyLogoResource: PresentationsQuery.Logo?) = Image(
            large = companyLogoResource?.large()?.url().orEmpty()
    )
}