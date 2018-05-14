package com.undabot.jobfair.booths.view.mappers

import com.undabot.jobfair.booths.entities.Booths
import com.undabot.jobfair.booths.view.models.BoothsViewModel
import javax.inject.Inject

class BoothsViewModelMapper
@Inject constructor(private val boothViewModelMapper: BoothViewModelMapper) {

    fun map(booths: Booths) = BoothsViewModel(booths.list.map { boothViewModelMapper.map(it) })
}