package com.undabot.jobfair.login.repository.model

import com.google.gson.annotations.SerializedName
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.core.entities.Image

data class CompanyLocal(
    @SerializedName("id") val id: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("thumb") val thumb: String?
) {

    fun toCompany(): Company =
        Company(
            id = id.orEmpty(),
            name = name.orEmpty(),
            image = Image(thumb = thumb.orEmpty())
        )

    companion object {
        fun fromCompany(company: Company?) = company?.let {
            CompanyLocal(
                id = it.id,
                name = it.name,
                thumb = it.image.thumb
            )
        }
    }
}