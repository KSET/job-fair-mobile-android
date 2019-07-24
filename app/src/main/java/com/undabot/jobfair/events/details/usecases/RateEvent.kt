package com.undabot.jobfair.events.details.usecases

import android.annotation.SuppressLint
import com.undabot.jobfair.core.schedulers.MainScheduler
import com.undabot.jobfair.events.details.models.RateParams
import com.undabot.jobfair.events.details.repository.EventDetailsRepository
import com.undabot.jobfair.events.details.repository.RateResult
import javax.inject.Inject

class RateEvent @Inject constructor(
    private val repository: EventDetailsRepository,
    private val mainScheduler: MainScheduler) {

    @SuppressLint("CheckResult")
    operator fun invoke(params: RateParams, presenter: RatesEvent) {
        repository.rateEvent(params).observeOn(mainScheduler.get())
            .subscribe(
                {
                    when (it) {
                        is RateResult.Success -> presenter.ratingSuccess()
                        is RateResult.Failure -> presenter.ratingFailed()
                    }
                },
                { presenter.ratingFailed() }
            )
    }

    interface RatesEvent {
        fun ratingSuccess()
        fun ratingFailed()
    }
}