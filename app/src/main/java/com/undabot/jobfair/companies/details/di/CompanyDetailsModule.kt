package com.undabot.jobfair.companies.details.di

import com.undabot.jobfair.companies.details.view.CompanyDetails
import com.undabot.jobfair.companies.details.view.CompanyDetailsCoordinator
import com.undabot.jobfair.companies.details.view.CompanyDetailsPresenter
import com.undabot.jobfair.companies.repository.CompaniesRepository
import com.undabot.jobfair.companies.repository.CompaniesRepositoryImpl
import com.undabot.jobfair.companies.repository.mappers.CompanyDetailsNetworkMapper
import com.undabot.jobfair.companies.repository.mappers.CompanyNetworkMapper
import com.undabot.jobfair.companies.repository.mappers.IndustryNetworkMapper
import com.undabot.jobfair.core.di.scope.PerFragment
import com.undabot.jobfair.core.schedulers.WorkerScheduler
import com.undabot.jobfair.networking.services.ResourceApiService
import dagger.Module
import dagger.Provides

@Module
class CompanyDetailsModule {

    @Provides
    @PerFragment
    fun coordinator(coordinator: CompanyDetailsCoordinator): CompanyDetails.Coordinator = coordinator

    @Provides
    @PerFragment
    fun presenter(presenter: CompanyDetailsPresenter): CompanyDetails.Presenter = presenter

    @Provides
    @PerFragment
    fun repository(
            api: ResourceApiService,
            workerScheduler: WorkerScheduler): CompaniesRepository =
            CompaniesRepositoryImpl(api, workerScheduler,
                    IndustryNetworkMapper(), CompanyDetailsNetworkMapper(), CompanyNetworkMapper())
}