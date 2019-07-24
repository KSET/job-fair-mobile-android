package com.undabot.jobfair.booths.repository.mappers

import com.undabot.jobfair.BoothsQuery
import com.undabot.jobfair.booths.entities.Booth

class BoothMapper(
        private val companyMapper: BoothCompanyMapper = BoothCompanyMapper(),
        private val locationMapper: BoothLocationMapper = BoothLocationMapper()
) {

    fun map(boothResource: BoothsQuery.Booth) = Booth(
            id = boothResource.id().orEmpty(),
            locationName = boothResource.location().orEmpty(),
            location = locationMapper.map(boothResource.geolocation()),
            company = companyMapper.map(boothResource.company())
    )
}