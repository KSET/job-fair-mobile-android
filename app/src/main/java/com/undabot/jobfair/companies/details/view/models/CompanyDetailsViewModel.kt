package com.undabot.jobfair.companies.details.view.models

import com.undabot.jobfair.booths.entities.Location
import com.undabot.jobfair.booths.entities.LocationInfo

data class CompanyDetailsViewModel(
        val id: String = "",
        val name: String = "",
        val booth: LocationInfo = LocationInfo("", Location()),
        val websiteUrl: String = "",
        val industry: String = "",
        val logoUrl: String = "",
        val description: String = "",
        val presentationData: String = "",
        val workshopData: String = "",
        val cocktailName: String = ""
)