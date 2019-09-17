package com.undabot.jobfair.resetpassword.view

import com.undabot.jobfair.core.view.AbsCoordinator
import com.undabot.jobfair.resetpassword.usecases.ResetPassword
import javax.inject.Inject

class ResetPasswordCoordinator @Inject constructor(
    presenter: ResetPasswordContract.Presenter,
    private val resetPassword: ResetPassword
) : AbsCoordinator<ResetPasswordContract.View, ResetPasswordContract.Presenter>(presenter),
    ResetPasswordContract.Coordinator {

    override fun requestPasswordReset(email: String) {
        resetPassword(email, presenter)
    }

    override fun onUnbind() {
        resetPassword.cancel()
    }
}
