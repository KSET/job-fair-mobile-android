package com.undabot.jobfair.companies.view.models

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class CompanyViewModel(
    val id: String,
    val logoUrl: String,
    val name: String
) : Parcelable