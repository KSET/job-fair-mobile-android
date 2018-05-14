package com.undabot.jobfair.events.repository.mappers

import PresentationsQuery
import com.undabot.jobfair.core.entities.Image

class PresentationPresentersImageMapper {

    fun map(presentersImageResource: PresentationsQuery.Presenter_photo?) =
            Image(thumb = presentersImageResource?.thumb()?.url().orEmpty())
}