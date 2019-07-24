package com.undabot.jobfair.companies.view.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class IndustryViewModel(
    val id: String,
    val name: String,
    val companies: List<String>
) : Parcelable {
    override fun toString(): String = name
}