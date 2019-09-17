package com.undabot.jobfair.resetpassword.usecases

import com.undabot.jobfair.core.schedulers.MainScheduler
import com.undabot.jobfair.login.repository.UserRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ResetPassword @Inject constructor(
    private val repository: UserRepository,
    private val mainScheduler: MainScheduler
) {
    private val disposables = CompositeDisposable()

    operator fun invoke(email: String, presenter: ResetsPassword) {
        val disposable = repository.resetPassword(email)
            .observeOn(mainScheduler.get())
            .subscribe(
                { presenter.displaySuccessfulReset() },
                { presenter.displayError() }
            )

        disposables.add(disposable)
    }

    fun cancel() {
        disposables.dispose()
        disposables.clear()
    }

    interface ResetsPassword {
        fun displaySuccessfulReset()
        fun displayError()
    }
}