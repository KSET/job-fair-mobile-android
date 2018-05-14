package com.undabot.jobfair.companies.list.di

import com.undabot.jobfair.companies.list.view.CompaniesContract
import com.undabot.jobfair.companies.list.view.CompaniesCoordinator
import com.undabot.jobfair.companies.list.view.CompaniesPresenter
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
class CompaniesModule {

    @Provides
    @PerFragment
    fun coordinator(coordinator: CompaniesCoordinator): CompaniesContract.Coordinator = coordinator

    @Provides
    @PerFragment
    fun presenter(presenter: CompaniesPresenter): CompaniesContract.Presenter = presenter

    @Provides
    @PerFragment
    fun repository(
            api: ResourceApiService,
            workerScheduler: WorkerScheduler): CompaniesRepository =
            CompaniesRepositoryImpl(api, workerScheduler,
                    IndustryNetworkMapper(), CompanyDetailsNetworkMapper(), CompanyNetworkMapper())
}