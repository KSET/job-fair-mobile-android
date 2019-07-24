package com.undabot.jobfair.review.view

import com.undabot.jobfair.core.view.AbsCoordinator
import com.undabot.jobfair.review.repository.model.ResumeReview
import com.undabot.jobfair.review.usecases.GetCompanyMemberId
import com.undabot.jobfair.review.usecases.SendResumeReview
import javax.inject.Inject

class StudentReviewCoordinator @Inject constructor(
    presenter: StudentReviewContract.Presenter,
    private val sendResumeReview: SendResumeReview,
    private val getCompanyMemberId: GetCompanyMemberId
) : AbsCoordinator<StudentReviewContract.View, StudentReviewContract.Presenter>(presenter),
    StudentReviewContract.Coordinator {

    override fun requestSendReview(review: ResumeReview) {
        sendResumeReview(review, presenter)
    }

    override fun requestCompanyMemberId() {
        getCompanyMemberId(presenter)
    }
}
