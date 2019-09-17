package com.undabot.jobfair.scanqr.mapper

import com.google.gson.Gson
import com.undabot.jobfair.scanqr.entity.QrCodeData
import javax.inject.Inject

class QrCodeDataJsonMapper @Inject constructor(private val gson: Gson) {
    fun toJson(qrCodeData: QrCodeData) = gson.toJson(qrCodeData)

    fun fromJson(json: String) = gson.fromJson<QrCodeData>(json, QrCodeData::class.java)
}