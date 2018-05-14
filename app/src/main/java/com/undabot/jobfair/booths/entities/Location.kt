package com.undabot.jobfair.booths.entities

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Location(
        val latitude: Double = 0.0,
        val longitude: Double = 0.0
): Parcelable