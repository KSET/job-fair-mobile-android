package com.undabot.jobfair.booths.entities

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class LocationInfo(val location: String = "",
                        val geolocation: Location = Location()
) : Parcelable