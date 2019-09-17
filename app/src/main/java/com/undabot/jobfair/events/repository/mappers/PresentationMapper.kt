package com.undabot.jobfair.events.repository.mappers

import com.undabot.jobfair.PresentationsQuery
import com.undabot.jobfair.booths.entities.Location
import com.undabot.jobfair.booths.entities.LocationInfo
import com.undabot.jobfair.events.entities.Event
import com.undabot.jobfair.events.entities.Presentation

class PresentationMapper(
    private val companyMapper: PresentationsCompanyMapper = PresentationsCompanyMapper(),
    private val presentersImageMapper: PresentationPresentersImageMapper = PresentationPresentersImageMapper()
) {

    fun map(presentation: PresentationsQuery.Presentation) = Presentation(
        id = presentation.id().orEmpty(),
        title = presentation.title().orEmpty(),
        description = presentation.description().orEmpty(),
        startTime = presentation.occures_at() ?: Event.DEFAULT_DATE_TIME,
        endTime = presentation.finishes_at() ?: Event.DEFAULT_DATE_TIME,
        company = companyMapper.map(presentation.company()),
        location = LocationInfo(
            location = presentation.place()?.name().orEmpty(),
            geolocation = Location(
                latitude = presentation.place()?.geolocation()?.latitude() ?: 0.0,
                longitude = presentation.place()?.geolocation()?.longitude() ?: 0.0
            )
        ),
        presentersBio = presentation.presenter_bio().orEmpty(),
        presentersImage = presentersImageMapper.map(presentation.presenter_photo())
    )
}