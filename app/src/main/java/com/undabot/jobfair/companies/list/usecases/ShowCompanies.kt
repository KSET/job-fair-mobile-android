package com.undabot.jobfair.companies.list.usecases

import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.companies.repository.CompaniesRepository
import com.undabot.jobfair.core.schedulers.MainScheduler
import javax.inject.Inject

class ShowCompanies @Inject constructor(val repository: CompaniesRepository,
                                        val mainScheduler: MainScheduler) {

    fun fetch(presenter: DisplaysCompanies) {
        repository.fetchCompanies()
                .observeOn(mainScheduler.get())
                .subscribe(
                        { presenter.displayCompanies(it) },
                        { presenter.displayError() }
                )
    }

    interface DisplaysCompanies {

        fun displayLoading()
        fun displayCompanies(items: List<Company>)
        fun displayError()
    }
}