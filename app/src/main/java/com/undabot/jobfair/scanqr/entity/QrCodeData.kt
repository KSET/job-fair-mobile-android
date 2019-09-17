package com.undabot.jobfair.scanqr.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QrCodeData(
    @SerializedName("resume_uid") val resumeId:String,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String
) : Parcelable {
    val fullName:String
        get() = "$firstName $lastName"
}