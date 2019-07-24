package com.undabot.jobfair.support.repository.mappers

import com.undabot.jobfair.OnSiteRequestMutation
import com.undabot.jobfair.support.entities.SupportItem
import com.undabot.jobfair.support.entities.SupportResult
import javax.inject.Inject

class SupportMapper @Inject constructor() {

    fun map(data: OnSiteRequestMutation.Data1?) =
        SupportResult(
            id = data?.id().orEmpty(),
            items = data?.items()?.map { it.map() } ?: emptyList()
        )

    private fun OnSiteRequestMutation.Item.map() = SupportItem(id(), amount())
}