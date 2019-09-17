package com.undabot.jobfair.login.models

import com.undabot.jobfair.companies.entities.Company

data class User(
    val id: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val resume: Resume?,
    val type: UserType = UserType.COMPANY,
    val companies: List<Company> = emptyList()
)