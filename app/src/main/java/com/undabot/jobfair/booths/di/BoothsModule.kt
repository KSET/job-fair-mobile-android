package com.undabot.jobfair.booths.di

import com.undabot.jobfair.booths.repository.BoothsRepository
import com.undabot.jobfair.booths.repository.BoothsRepositoryImpl
import com.undabot.jobfair.booths.repository.mappers.BoothMapper
import com.undabot.jobfair.booths.view.BoothsContract
import com.undabot.jobfair.booths.view.BoothsCoordinator
import com.undabot.jobfair.booths.view.BoothsPresenter
import com.undabot.jobfair.core.di.scope.PerFragment
import com.undabot.jobfair.core.schedulers.WorkerScheduler
import com.undabot.jobfair.networking.services.ResourceApiService
import dagger.Module
import dagger.Provides

@Module
class BoothsModule {

    @Provides
    @PerFragment
    fun coordinator(coordinator: BoothsCoordinator): BoothsContract.Coordinator = coordinator

    @Provides
    @PerFragment
    fun presenter(presenter: BoothsPresenter): BoothsContract.Presenter = presenter

    @Provides
    @PerFragment
    fun repository(resourceApiService: ResourceApiService,
                   workerScheduler: WorkerScheduler): BoothsRepository =
            BoothsRepositoryImpl(resourceApiService,
                    BoothMapper(),
                    workerScheduler)
}