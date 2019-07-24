package com.undabot.jobfair.splash.usecases

import com.undabot.jobfair.login.repository.UserRepository
import javax.inject.Inject

class InitializeApp @Inject constructor(private val repository: UserRepository) {

    operator fun invoke(presenter: InitializesApp) {
        val isLoggedIn = repository.isLoggedIn()

        if (isLoggedIn) {
            presenter.openHomeScreen()
        } else {
            presenter.showLoginTeaser()
        }
    }

    interface InitializesApp {
        fun openHomeScreen()
        fun showLoginTeaser()
    }
}