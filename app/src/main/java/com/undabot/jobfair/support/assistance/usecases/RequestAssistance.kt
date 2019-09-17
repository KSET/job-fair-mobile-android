package com.undabot.jobfair.support.assistance.usecases

import com.undabot.jobfair.core.schedulers.MainScheduler
import com.undabot.jobfair.login.repository.LoginPreferences
import com.undabot.jobfair.support.repository.SupportRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RequestAssistance @Inject constructor(
    private val mainScheduler: MainScheduler,
    private val loginPreferences: LoginPreferences,
    private val repository: SupportRepository
) {
    private val disposable = CompositeDisposable()

    operator fun invoke(note: String, presenter: RequestsAssistance) {
        val companyId = getCompanyId()

        if (companyId != null) {
            disposable.add(
                repository.requestAssistance(companyId, note)
                    .observeOn(mainScheduler.get())
                    .subscribe(
                        { presenter.assistanceSent(note) },
                        { presenter.assistanceError() }
                    )
            )
        }
    }

    fun stop() {
        disposable.clear()
    }

    private fun getCompanyId(): String? {
        val companies = loginPreferences.getUser()?.companies
        return if (companies != null && companies.isNotEmpty()) {
            companies[0].id
        } else {
            null
        }
    }

    interface RequestsAssistance {
        fun assistanceSent(note: String?)
        fun assistanceError()
    }
}