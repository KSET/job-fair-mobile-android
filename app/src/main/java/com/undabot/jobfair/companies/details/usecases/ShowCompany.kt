package com.undabot.jobfair.companies.details.usecases

import com.undabot.jobfair.companies.entities.DetailedCompany
import com.undabot.jobfair.companies.repository.CompaniesRepository
import com.undabot.jobfair.core.schedulers.MainScheduler
import javax.inject.Inject

class ShowCompany
@Inject
constructor(private val repository: CompaniesRepository,
            private val mainScheduler: MainScheduler) {

    fun forId(id: String, presenter: DisplaysCompanyDetail) {
        repository.fetchDetailsForCompany(id)
                .observeOn(mainScheduler.get())
                .subscribe(
                        { presenter.displayDetails(it) },
                        { presenter.displayError() })
    }

    interface DisplaysCompanyDetail {
        fun displayDetails(details: DetailedCompany)
        fun displayError()
    }
}