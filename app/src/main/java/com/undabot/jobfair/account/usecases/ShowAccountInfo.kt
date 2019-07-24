package com.undabot.jobfair.account.usecases

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.util.Log
import com.undabot.jobfair.core.schedulers.MainScheduler
import com.undabot.jobfair.login.models.UserType
import com.undabot.jobfair.login.repository.UserRepository
import com.undabot.jobfair.scanqr.mapper.UserQrCodeMapper
import javax.inject.Inject

class ShowAccountInfo @Inject constructor(
    private val repository: UserRepository,
    private val userQrCodeMapper: UserQrCodeMapper,
    private val mainScheduler: MainScheduler
) {

    @SuppressLint("CheckResult")
    operator fun invoke(presenter: ShowsAccountInfo) {
        if (!repository.isLoggedIn()) {
            return
        }
        repository.getLoggedInUserRemote()
            .observeOn(mainScheduler.get())
            .subscribe(
                { user ->
                    when (user?.type) {
                        UserType.COMPANY -> presenter.show(AccountInfo.Company(companies = user.companies))
                        UserType.STUDENT -> presenter.show(
                            AccountInfo.Student(
                                firstName = user.firstName,
                                lastName = user.lastName,
                                qrCode = userQrCodeMapper.map(user)
                            )
                        )
                        else -> Log.d("ShowAccountInfo", "Unsupported user type!")
                    }
                },
                {

                }
            )
    }

    interface ShowsAccountInfo {
        fun show(accountInfo: AccountInfo)
    }
}

sealed class AccountInfo {
    data class Student(val firstName: String, val lastName: String, val qrCode: Bitmap?) : AccountInfo()
    data class Company(val companies: List<com.undabot.jobfair.companies.entities.Company>) : AccountInfo()
}