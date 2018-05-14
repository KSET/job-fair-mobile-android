package com.undabot.jobfair.events.repository.mappers

import WorkshopsQuery
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
            location = workshop.location().orEmpty()
    )
}