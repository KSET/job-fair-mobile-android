package com.undabot.jobfair.events.repository.mappers

import WorkshopsQuery
import com.undabot.jobfair.core.entities.Image

class WorkshopsCompanyLogoMapper {

    fun map(companyLogoResource: WorkshopsQuery.Logo?) = Image(
            large = companyLogoResource?.large()?.url().orEmpty()
    )
}