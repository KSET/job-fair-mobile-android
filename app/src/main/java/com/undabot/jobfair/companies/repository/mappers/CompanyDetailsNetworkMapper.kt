package com.undabot.jobfair.companies.repository.mappers

import com.undabot.jobfair.CompanyDetailsQuery
import com.undabot.jobfair.booths.repository.mappers.BoothLocationMapper
import com.undabot.jobfair.companies.entities.DetailedCompany
import com.undabot.jobfair.companies.entities.DetailedCompany.Companion.DEFAULT_DATE_TIME

class CompanyDetailsNetworkMapper(
        private val boothLocationMapper: BoothLocationMapper = BoothLocationMapper()
) {

    fun map(resource: CompanyDetailsQuery.Company) =
            DetailedCompany(id = resource.id().orEmpty(),
                    name = resource.name().orEmpty(),
                    logoUrl = resource.logo()?.medium()?.url().orEmpty(),
                    websiteUrl = resource.homepage_url().orEmpty(),
                    industryName = resource.industry()?.name().orEmpty(),
                    workshopName = resource.workshop()?.name().orEmpty(),
                    workshopDate = resource.workshop()?.occures_at() ?: DEFAULT_DATE_TIME,
                    presentationName = resource.presentation()?.title().orEmpty(),
                    presentationDate = resource.presentation()?.occures_at() ?: DEFAULT_DATE_TIME,
                    description = resource.short_description().orEmpty(),
                    cocktail = resource.cocktail()?.name().orEmpty(),
                    boothLocation = resource.booth()?.location().orEmpty(),
                    boothGeolocation = boothLocationMapper.map(resource.booth()?.geolocation()))
}