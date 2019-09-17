package com.undabot.jobfair.review.usecases

import android.annotation.SuppressLint
import com.undabot.jobfair.core.schedulers.MainScheduler
import com.undabot.jobfair.login.repository.UserRepository
import javax.inject.Inject

class GetCompanyMemberId @Inject constructor(
    private val repository: UserRepository,
    private val mainScheduler: MainScheduler
) {

    @SuppressLint("CheckResult")
    operator fun invoke(presenter: GetsCompanyMemberId) {
        val companyMemberId = repository.getLoggedInUserLocal()?.id.orEmpty()
        if (companyMemberId.isNullOrBlank()) {
            presenter.displayError()
        } else {
            presenter.saveCompanyMemberId(companyMemberId)
        }
    }

    interface GetsCompanyMemberId {
        fun saveCompanyMemberId(companyMemberId: String)
        fun displayError()
    }
}