package com.undabot.jobfair.booths.view.mappers

import com.undabot.jobfair.booths.entities.Booth
import com.undabot.jobfair.booths.view.models.BoothViewModel
import javax.inject.Inject

class BoothViewModelMapper @Inject constructor() {

    fun map(booth: Booth) = BoothViewModel(
            id = booth.id,
            title = booth.company.name,
            snippet = booth.locationName,
            location = booth.location,
            imageUrl = booth.company.image.thumb,
            company = booth.company
    )
}