package com.undabot.jobfair.booths.repository

import com.undabot.jobfair.booths.repository.mappers.BoothMapper
import com.undabot.jobfair.core.schedulers.WorkerScheduler
import com.undabot.jobfair.networking.services.ResourceApiService
import io.reactivex.Single

class BoothsRepositoryImpl
constructor(
        private val resourceApiService: ResourceApiService,
        private val boothMapper: BoothMapper,
        private val workerScheduler: WorkerScheduler
) : BoothsRepository {

    override fun booths(): Single<BoothsResult> {
        return resourceApiService.booths()
                .map { list -> BoothsResult(list.map { boothMapper.map(it) }) }
                .subscribeOn(workerScheduler.get())
    }
}