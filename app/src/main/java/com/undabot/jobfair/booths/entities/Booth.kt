package com.undabot.jobfair.booths.entities

import com.undabot.jobfair.companies.entities.Company

data class Booth(
        val id: String = "",
        val locationName: String = "",
        val location: Location = Location(),
        val company: Company = Company()
)