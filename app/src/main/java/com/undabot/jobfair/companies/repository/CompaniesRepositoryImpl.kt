package com.undabot.jobfair.companies.repository

import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.companies.entities.DetailedCompany
import com.undabot.jobfair.companies.entities.Industry
import com.undabot.jobfair.companies.repository.mappers.CompanyDetailsNetworkMapper
import com.undabot.jobfair.companies.repository.mappers.CompanyNetworkMapper
import com.undabot.jobfair.companies.repository.mappers.IndustryNetworkMapper
import com.undabot.jobfair.core.schedulers.WorkerScheduler
import com.undabot.jobfair.networking.services.ResourceApiService
import io.reactivex.Single

class CompaniesRepositoryImpl constructor(
        private val api: ResourceApiService,
        private val workerScheduler: WorkerScheduler,
        private val industryNetworkMapper: IndustryNetworkMapper,
        private val companyDetailsNetworkMapper: CompanyDetailsNetworkMapper,
        private val companyNetworkMapper: CompanyNetworkMapper
) : CompaniesRepository {

    override fun fetchDetailsForCompany(id: String): Single<DetailedCompany> =
            api.company(id).subscribeOn(workerScheduler.get())
                    .map { companyDetailsNetworkMapper.map(it) }

    override fun fetchIndustries(): Single<List<Industry>> =
            api.industries()
                    .subscribeOn(workerScheduler.get())
                    .map {
                        listOf(Industry.allItems())
                                .plus(it.map {
                                    industryNetworkMapper.map(it)
                                })
                    }

    override fun fetchCompanies(): Single<List<Company>> =
            api.companies()
                    .subscribeOn(workerScheduler.get())
                    .map { it.map { companyNetworkMapper.map(it) } }
}