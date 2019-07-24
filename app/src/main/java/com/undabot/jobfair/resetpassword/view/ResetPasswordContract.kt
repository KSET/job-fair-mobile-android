package com.undabot.jobfair.resetpassword.view

import com.undabot.jobfair.core.view.BaseCoordinator
import com.undabot.jobfair.core.view.BasePresenter
import com.undabot.jobfair.resetpassword.usecases.ResetPassword

interface ResetPasswordContract {

    interface Coordinator : BaseCoordinator<View> {
        fun requestPasswordReset(email: String)
    }

    interface Presenter : BasePresenter<View>,
        ResetPassword.ResetsPassword

    interface View {
        fun displaySuccessfulReset()
        fun showGeneralError()
    }
}