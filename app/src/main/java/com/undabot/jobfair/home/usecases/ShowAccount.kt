package com.undabot.jobfair.home.usecases

import com.undabot.jobfair.login.repository.UserRepository
import javax.inject.Inject

class ShowAccount @Inject constructor(
    private val repository: UserRepository
) {

    operator fun invoke(presenter: ShowsAccount) {
        if (repository.isLoggedIn()) {
            presenter.showAccountScreen()
        } else {
            presenter.showLoginScreen()
        }
    }

    interface ShowsAccount {
        fun showLoginScreen()
        fun showAccountScreen()
    }
}