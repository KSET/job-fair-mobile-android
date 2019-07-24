package com.undabot.jobfair.login.repository.mapper

import com.undabot.jobfair.CurrentUserQuery
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.core.entities.Image
import com.undabot.jobfair.login.models.Resume
import com.undabot.jobfair.login.models.User
import com.undabot.jobfair.login.models.UserType
import javax.inject.Inject

class CurrentUserMapper @Inject constructor() {

    fun map(user: CurrentUserQuery.Current_user?) =
        if (user != null) {
            User(
                id = user.id().orEmpty(),
                email = user.email().orEmpty(),
                resume = resumeFrom(user.resume()),
                firstName = user.first_name().orEmpty(),
                lastName = user.last_name().orEmpty(),
                type = userTypeFrom(user),
                companies = companiesFrom(user.companies())
            )
        } else {
            throw IllegalArgumentException("Parameter user cannot be null")
        }

    private fun resumeFrom(resume: CurrentUserQuery.Resume?) =
        if (resume != null) {
            Resume(
                id = resume.id().orEmpty(),
                uid = resume.uid().orEmpty()
            )
        } else {
            Resume.NULL_RESUME
        }

    private fun companiesFrom(companies: List<CurrentUserQuery.Company>?): List<Company> {
        return companies?.map {
            Company(id = it.id().orEmpty(),
                image = Image(thumb = it.logo()?.thumb()?.url().orEmpty()),
                name = it.name().orEmpty()
            )
        } ?: emptyList()
    }

    private fun userTypeFrom(user: CurrentUserQuery.Current_user): UserType {
        return if (!user.companies().isNullOrEmpty()) {
            UserType.COMPANY
        } else if (user.role()?.name.equals("student", true)) {
            UserType.STUDENT
        } else {
            UserType.ANONYMOUS
        }
    }
}