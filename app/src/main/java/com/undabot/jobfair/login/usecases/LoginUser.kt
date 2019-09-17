package com.undabot.jobfair.login.usecases

import com.undabot.jobfair.core.schedulers.MainScheduler
import com.undabot.jobfair.login.repository.UserRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class LoginUser @Inject constructor(
    private val repository: UserRepository,
    private val mainScheduler: MainScheduler
) {
    private val disposables = CompositeDisposable()

    operator fun invoke(email: String, password: String, presenter: DisplaysHomeScreen) {
        val disposable = repository.login(email, password)
            .observeOn(mainScheduler.get())
            .subscribe(
                { presenter.showHomeScreen() },
                { presenter.displayError() }
            )

        disposables.add(disposable)
    }

    fun cancel() {
        disposables.dispose()
        disposables.clear()
    }

    interface DisplaysHomeScreen {
        fun showHomeScreen()
        fun displayError()
    }
}