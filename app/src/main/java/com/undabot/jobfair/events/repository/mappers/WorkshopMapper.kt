package com.undabot.jobfair.events.repository.mappers

import com.undabot.jobfair.WorkshopsQuery
import com.undabot.jobfair.booths.entities.Location
import com.undabot.jobfair.booths.entities.LocationInfo
import com.undabot.jobfair.core.constants.DEFAULT_LOCATION
import com.undabot.jobfair.events.entities.Event
import com.undabot.jobfair.events.entities.Workshop

class WorkshopMapper(
    private val companyMapper: WorkshopsCompanyMapper = WorkshopsCompanyMapper()
) {

    fun map(workshop: WorkshopsQuery.Workshop) = Workshop(
        id = workshop.id().orEmpty(),
        title = workshop.name().orEmpty(),
        description = workshop.description().orEmpty(),
        startTime = workshop.occures_at() ?: Event.DEFAULT_DATE_TIME,
        endTime = workshop.finishes_at() ?: Event.DEFAULT_DATE_TIME,
        company = companyMapper.map(workshop.company()),
        location = LocationInfo(
            location = workshop.place()?.name().orEmpty(),
            geolocation = workshop.place()?.geolocation()?.let {
                Location(
                    latitude = workshop.place()?.geolocation()?.latitude() ?: 0.0,
                    longitude = workshop.place()?.geolocation()?.longitude() ?: 0.0
                )
            } ?: DEFAULT_LOCATION
        )
    )
}