package com.undabot.jobfair.review.view

import com.undabot.jobfair.core.view.AbsPresenter
import javax.inject.Inject

class StudentReviewPresenter @Inject constructor()
    : AbsPresenter<StudentReviewContract.View>(),
    StudentReviewContract.Presenter {

    override fun finishScreen() = onView {
        it.finishScreen()
    }

    override fun saveCompanyMemberId(companyMemberId: String) = onView {
        it.saveCompanyMemberId(companyMemberId)
    }

    override fun displayError() = onView {
        it.showGeneralError()
    }
}