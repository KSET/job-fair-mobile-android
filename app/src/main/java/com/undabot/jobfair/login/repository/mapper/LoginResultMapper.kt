package com.undabot.jobfair.login.repository.mapper

import com.undabot.jobfair.LoginMutation
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.core.entities.Image
import com.undabot.jobfair.login.models.LoginResult
import com.undabot.jobfair.login.models.Resume
import com.undabot.jobfair.login.models.User
import com.undabot.jobfair.login.models.UserType
import javax.inject.Inject

class LoginResultMapper @Inject constructor() {

    fun map(login: LoginMutation.Login?) =
        if (login != null) {
            LoginResult(
                user = userFrom(login.user()),
                token = login.token()
            )
        } else {
            throw IllegalArgumentException("Parameter login cannot be null")
        }

    private fun userFrom(user: LoginMutation.User?) = user?.let {
        User(
            id = it.id().orEmpty(),
            email = it.email().orEmpty(),
            resume = resumeFrom(it.resume()),
            firstName = it.first_name().orEmpty(),
            lastName = it.last_name().orEmpty(),
            type = userTypeFrom(it),
            companies = companiesFrom(user.companies())
        )
    }

    private fun resumeFrom(resume: LoginMutation.Resume?) =
        if (resume != null) {
            Resume(
                id = resume.id().orEmpty(),
                uid = resume.uid().orEmpty()
            )
        } else {
            Resume.NULL_RESUME
        }

    private fun companiesFrom(companies: List<LoginMutation.Company>?): List<Company> {
        return companies?.map {
            Company(id = it.id().orEmpty(),
                image = Image(thumb = it.logo()?.thumb()?.url().orEmpty()),
                name = it.name().orEmpty()
            )
        } ?: emptyList()
    }

    private fun userTypeFrom(user: LoginMutation.User): UserType {
        return if (!user.companies().isNullOrEmpty()) {
            UserType.COMPANY
        } else if (user.role()?.name.equals("student", true)) {
            UserType.STUDENT
        } else {
            UserType.ANONYMOUS
        }
    }
}