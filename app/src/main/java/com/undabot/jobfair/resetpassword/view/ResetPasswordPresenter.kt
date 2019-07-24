package com.undabot.jobfair.resetpassword.view

import com.undabot.jobfair.core.view.AbsPresenter
import javax.inject.Inject

class ResetPasswordPresenter @Inject constructor()
    : AbsPresenter<ResetPasswordContract.View>(),
    ResetPasswordContract.Presenter {

    override fun displaySuccessfulReset() = onView {
        it.displaySuccessfulReset()
    }

    override fun displayError() = onView {
        it.showGeneralError()
    }
}