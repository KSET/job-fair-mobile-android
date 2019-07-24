package com.undabot.jobfair.review.repository

import com.undabot.jobfair.review.repository.model.ResumeReview
import io.reactivex.Completable

interface ResumeRepository {

    fun postReview(review: ResumeReview): Completable
}