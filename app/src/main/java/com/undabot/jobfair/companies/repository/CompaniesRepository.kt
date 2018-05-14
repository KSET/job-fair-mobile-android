package com.undabot.jobfair.companies.repository

import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.companies.entities.DetailedCompany
import com.undabot.jobfair.companies.entities.Industry
import io.reactivex.Single

interface CompaniesRepository {
    fun fetchIndustries(): Single<List<Industry>>
    fun fetchCompanies(): Single<List<Company>>
    fun fetchDetailsForCompany(id: String): Single<DetailedCompany>
}