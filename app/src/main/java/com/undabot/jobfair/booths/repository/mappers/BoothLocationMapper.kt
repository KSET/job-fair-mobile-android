package com.undabot.jobfair.booths.repository.mappers

import com.undabot.jobfair.booths.entities.Location

class BoothLocationMapper {

    private val latitudePosition = 0
    private val longitudePosition = 1
    private val bySeparator = ","

    fun map(geolocation: String?) = Location(
            latitude = getValueForPosition(geolocation, latitudePosition),
            longitude = getValueForPosition(geolocation, longitudePosition)
    )

    private fun getValueForPosition(geolocation: String?, position: Int) =
            geolocation?.split(bySeparator)?.getOrNull(position)?.toDoubleOrNull() ?: 0.0
}