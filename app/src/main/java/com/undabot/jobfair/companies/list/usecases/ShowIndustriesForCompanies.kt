package com.undabot.jobfair.companies.list.usecases

import com.undabot.jobfair.companies.entities.Industry
import com.undabot.jobfair.companies.repository.CompaniesRepository
import com.undabot.jobfair.core.schedulers.MainScheduler
import javax.inject.Inject

class ShowIndustriesForCompanies @Inject constructor(private val repository: CompaniesRepository,
                                                     private val mainScheduler: MainScheduler) {

    fun fetch(presenter: DisplaysIndustries) {
        repository.fetchIndustries()
                .observeOn(mainScheduler.get())
                .subscribe(
                        { presenter.displayIndustries(it) },
                        { presenter.displayError() }
                )
    }

    interface DisplaysIndustries {
        fun displayIndustries(list: List<Industry>)
        fun displayError()
    }
}