package com.undabot.jobfair.companies.details.mappers

import com.undabot.jobfair.booths.entities.LocationInfo
import com.undabot.jobfair.companies.details.view.models.CompanyDetailsViewModel
import com.undabot.jobfair.companies.entities.DetailedCompany
import com.undabot.jobfair.utils.DateTimeFormatter
import org.joda.time.DateTime
import javax.inject.Inject

private const val EMPTY = ""

class CompanyDetailsMapper
@Inject constructor(
        private val dateTimeFormatter: DateTimeFormatter
) {
    fun map(company: DetailedCompany) = CompanyDetailsViewModel(
            id = company.id,
            name = company.name,
            booth = LocationInfo(company.boothLocation, company.boothGeolocation),
            websiteUrl = company.websiteUrl,
            industry = company.industryName,
            logoUrl = company.logoUrl,
            description = company.description,
            presentationData = mapEvent(company.presentationName, company.presentationDate),
            workshopData = mapEvent(company.workshopName, company.workshopDate),
            cocktailName = if (company.cocktail.isEmpty()) EMPTY else company.cocktail)

    private fun mapEvent(title: String, dateTime: DateTime): String =
            if (title.isEmpty()) EMPTY
            else "$title • ${dateTimeFormatter.formatToDateAndTime(dateTime)}"
}