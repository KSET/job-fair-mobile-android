package com.undabot.jobfair.login.repository.model

import com.google.gson.annotations.SerializedName
import com.undabot.jobfair.login.models.User
import com.undabot.jobfair.login.models.UserType

data class UserLocal(
    @SerializedName("id") val id: String?,
    @SerializedName("email") val email: String?,
    @SerializedName("firstName") val firstName: String?,
    @SerializedName("lastName") val lastName: String?,
    @SerializedName("resume") val resume: ResumeLocal?,
    @SerializedName("type") val type: String?,
    @SerializedName("companies") val companies: List<CompanyLocal>?
) {
    fun toUser(): User =
        User(
            id = id.orEmpty(),
            email = email.orEmpty(),
            firstName = firstName.orEmpty(),
            lastName = lastName.orEmpty(),
            resume = resume?.toResume(),
            type = UserType.valueOf(type ?: UserType.ANONYMOUS.value),
            companies = companies?.map { companyLocal -> companyLocal.toCompany() } ?: emptyList()
        )

    companion object {
        fun fromUser(user: User?) = user?.run {
            UserLocal(
                id = id,
                email = email,
                firstName = firstName,
                lastName = lastName,
                resume = ResumeLocal.fromResume(resume),
                type = type.value,
                companies = companies.map { company -> CompanyLocal.fromCompany(company)!! }
            )
        }
    }
}