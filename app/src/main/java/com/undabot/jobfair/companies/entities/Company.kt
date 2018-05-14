package com.undabot.jobfair.companies.entities

import com.undabot.jobfair.core.entities.Image

data class Company(
        val id: String = "",
        val image: Image = Image(),
        val name: String = ""
)