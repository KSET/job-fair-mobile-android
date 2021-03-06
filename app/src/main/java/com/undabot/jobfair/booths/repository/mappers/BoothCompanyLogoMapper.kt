package com.undabot.jobfair.booths.repository.mappers

import com.undabot.jobfair.BoothsQuery
import com.undabot.jobfair.core.entities.Image

class BoothCompanyLogoMapper {

    fun map(companyLogoResource: BoothsQuery.Logo?) = Image(
            thumb = companyLogoResource?.thumb()?.url().orEmpty()
    )
}