package com.undabot.jobfair.networking.services

import BoothsQuery
import CompaniesQuery
import CompanyDetailsQuery
import IndustriesQuery
import PresentationsQuery
import WorkshopsQuery
import io.reactivex.Single

interface ResourceApiService {

    fun presentations(): Single<List<PresentationsQuery.Presentation>>

    fun workshops(): Single<List<WorkshopsQuery.Workshop>>

    fun companies(): Single<List<CompaniesQuery.Company>>

    fun industries(): Single<List<IndustriesQuery.Industry>>

    fun company(id: String): Single<CompanyDetailsQuery.Company>

    fun booths(): Single<List<BoothsQuery.Booth>>
}