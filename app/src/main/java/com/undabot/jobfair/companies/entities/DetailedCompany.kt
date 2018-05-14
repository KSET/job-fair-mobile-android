package com.undabot.jobfair.companies.entities

import com.undabot.jobfair.booths.entities.Location
import org.joda.time.DateTime

data class DetailedCompany(
        val id: String = "",
        val name: String = "",
        val logoUrl: String = "",
        val websiteUrl: String = "",
        val industryName: String = "",
        val workshopName: String = "",
        val workshopDate: DateTime,
        val presentationName: String = "",
        val presentationDate: DateTime,
        val description: String = "",
        val cocktail: String = "",
        val boothLocation: String = "",
        val boothGeolocation: Location = Location()
) {
    companion object {
        val DEFAULT_DATE_TIME: DateTime = DateTime()
    }
}
