package com.undabot.jobfair.events.repository.mappers

import PresentationsQuery
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
            location = presentation.location().orEmpty(),
            presentersBio = presentation.presenter_bio().orEmpty(),
            presentersImage = presentersImageMapper.map(presentation.presenter_photo())
    )
}