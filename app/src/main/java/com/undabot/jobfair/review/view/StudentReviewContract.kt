package com.undabot.jobfair.review.view

import com.undabot.jobfair.core.view.BaseCoordinator
import com.undabot.jobfair.core.view.BasePresenter
import com.undabot.jobfair.review.repository.model.ResumeReview
import com.undabot.jobfair.review.usecases.GetCompanyMemberId
import com.undabot.jobfair.review.usecases.SendResumeReview

interface StudentReviewContract {

    interface Coordinator : BaseCoordinator<View> {
        fun requestSendReview(review: ResumeReview)
        fun requestCompanyMemberId()
    }

    interface Presenter : BasePresenter<View>,
        SendResumeReview.SendsReview,
        GetCompanyMemberId.GetsCompanyMemberId

    interface View {
        fun finishScreen()
        fun saveCompanyMemberId(companyMemberId: String)
        fun showGeneralError()
    }
}