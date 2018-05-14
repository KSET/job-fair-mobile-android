package com.undabot.jobfair.booths.view.models

import com.undabot.jobfair.booths.entities.Location
import com.undabot.jobfair.companies.entities.Company

data class BoothsViewModel(val list: List<BoothViewModel> = arrayListOf())

data class BoothViewModel(
        val id: String = "",
        val title: String = "",
        val snippet: String = "",
        val imageUrl: String = "",
        val location: Location,
        val company: Company = Company()
)