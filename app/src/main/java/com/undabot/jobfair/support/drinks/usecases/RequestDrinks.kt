package com.undabot.jobfair.support.drinks.usecases

import com.undabot.jobfair.core.schedulers.MainScheduler
import com.undabot.jobfair.login.repository.LoginPreferences
import com.undabot.jobfair.support.repository.SupportRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class RequestDrinks @Inject constructor(
    private val mainScheduler: MainScheduler,
    private val loginPreferences: LoginPreferences,
    private val repository: SupportRepository
) {
    private val disposable = CompositeDisposable()

    operator fun invoke(drinks: Map<Int, Int>, presenter: RequestsDrinks) {
        val companyId = getCompanyId()

        if (companyId != null) {
            disposable.add(
                repository.requestDrinks(companyId, drinks)
                    .observeOn(mainScheduler.get())
                    .subscribe(
                        { presenter.drinksRequested(drinks) },
                        { presenter.drinksRequestFailed(); throw it }
                    )
            )
        }
    }

    private fun getCompanyId(): String? {
        val companies = loginPreferences.getUser()?.companies
        return if (companies != null && companies.isNotEmpty()) {
            companies[0].id
        } else {
            null
        }
    }

    interface RequestsDrinks {
        fun drinksRequested(drinks: Map<Int, Int>)
        fun drinksRequestFailed()
    }
}