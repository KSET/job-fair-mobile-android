package com.undabot.jobfair.review.repository

import com.undabot.jobfair.core.schedulers.WorkerScheduler
import com.undabot.jobfair.networking.services.ResourceApiService
import com.undabot.jobfair.review.repository.model.ResumeReview
import io.reactivex.Completable
import javax.inject.Inject

class ResumeRepositoryImpl @Inject constructor(
    private val api: ResourceApiService,
    private val workerScheduler: WorkerScheduler
) : ResumeRepository {

    override fun postReview(review: ResumeReview): Completable = with(review) {
        api.resumeReview(clientMutationId, companyMemberId, resumeUid, notes, social, ambition, skill)
            .subscribeOn(workerScheduler.get())
            .ignoreElement()
    }
}