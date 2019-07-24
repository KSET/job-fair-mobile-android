package com.undabot.jobfair.events.details.repository

import com.undabot.jobfair.EventReviewMutation
import com.undabot.jobfair.core.schedulers.WorkerScheduler
import com.undabot.jobfair.events.details.models.RateParams
import com.undabot.jobfair.networking.services.ResourceApiService
import io.reactivex.Single
import javax.inject.Inject

class EventDetailsRepository @Inject constructor(
    private val resourceApiService: ResourceApiService,
    private val workerScheduler: WorkerScheduler) {

    fun rateEvent(params: RateParams): Single<RateResult> =
        resourceApiService.eventReview(
            presentationId = params.presentationId,
            workshopId = params.workshopId,
            value = params.value
        ).subscribeOn(workerScheduler.get())
            .map { data -> handleResult(data, params) }

    private fun handleResult(data: EventReviewMutation.Data1?, params: RateParams) =
        when (data) {
            null -> RateResult.Failure
            else -> RateResult.Success(value = data.value() ?: params.value)
        }
}

sealed class RateResult {
    data class Success(val value: Int) : RateResult()
    object Failure : RateResult()
}