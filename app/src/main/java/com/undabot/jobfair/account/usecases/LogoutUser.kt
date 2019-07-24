package com.undabot.jobfair.account.usecases

import com.undabot.jobfair.login.repository.UserRepository
import javax.inject.Inject

class LogoutUser @Inject constructor(private val repository: UserRepository) {

    operator fun invoke(presenter: LogoutsUser) {
        repository.logout()
        presenter.notifySuccessLogout()
    }

    interface LogoutsUser {
        fun notifySuccessLogout()
    }

}