package com.undabot.jobfair.login.view

import com.undabot.jobfair.core.view.AbsCoordinator
import com.undabot.jobfair.login.usecases.LoginUser
import javax.inject.Inject

class LoginCoordinator @Inject constructor(
    presenter: LoginContract.Presenter,
    private val loginUser: LoginUser
) : AbsCoordinator<LoginContract.View, LoginContract.Presenter>(presenter),
    LoginContract.Coordinator {

    override fun loginRequested(email: String, password: String) {
        loginUser(email, password, presenter)
    }

    override fun onUnbind() {
        loginUser.cancel()
    }
}
