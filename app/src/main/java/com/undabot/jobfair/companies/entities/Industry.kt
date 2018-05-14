package com.undabot.jobfair.companies.entities

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Industry(val id: String = "",
                    val name: String = "",
                    val companyIds: List<String> = emptyList()) : Parcelable {

    companion object {
        const val ALL_ITEMS_FILTER = "-1"
        fun allItems() = Industry(ALL_ITEMS_FILTER, "All", emptyList())
    }
}