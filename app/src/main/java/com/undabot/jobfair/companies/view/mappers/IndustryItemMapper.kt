package com.undabot.jobfair.companies.view.mappers

import com.undabot.jobfair.companies.entities.Industry
import com.undabot.jobfair.companies.view.models.IndustryViewModel
import javax.inject.Inject

class IndustryItemMapper @Inject constructor() {

    fun map(industry: Industry) = IndustryViewModel(
            industry.id, industry.name, industry.companyIds)
}