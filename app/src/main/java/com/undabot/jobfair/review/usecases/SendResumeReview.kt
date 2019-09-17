package com.undabot.jobfair.review.usecases

import android.annotation.SuppressLint
import com.undabot.jobfair.core.schedulers.MainScheduler
import com.undabot.jobfair.review.repository.ResumeRepository
import com.undabot.jobfair.review.repository.model.ResumeReview
import javax.inject.Inject

class SendResumeReview @Inject constructor(
    private val repository: ResumeRepository,
    private val mainScheduler: MainScheduler
) {

    @SuppressLint("CheckResult")
    operator fun invoke(review: ResumeReview, presenter: SendsReview) {
        repository.postReview(review)
            .observeOn(mainScheduler.get())
            .subscribe(
                { presenter.finishScreen() },
                { presenter.displayError() }
            )
    }

    interface SendsReview {
        fun finishScreen()
        fun displayError()
    }
}