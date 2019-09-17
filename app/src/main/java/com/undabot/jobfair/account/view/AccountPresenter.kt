package com.undabot.jobfair.account.view

import com.undabot.jobfair.account.usecases.AccountInfo
import com.undabot.jobfair.account.view.AccountContract.Presenter
import com.undabot.jobfair.account.view.AccountContract.View
import com.undabot.jobfair.account.view.model.AccountViewModel
import com.undabot.jobfair.core.view.AbsPresenter
import javax.inject.Inject

class AccountPresenter @Inject constructor() : AbsPresenter<View>(), Presenter {

    override fun notifySuccessLogout() = onView {
        it.restartApplication()
    }

    override fun show(accountInfo: AccountInfo) {
        when (accountInfo) {
            is AccountInfo.Company -> showCompanyInfo(accountInfo)
            is AccountInfo.Student -> showStudentInfo(accountInfo)
        }
    }

    private fun showCompanyInfo(companyInfo: AccountInfo.Company) {
        val viewModels: List<AccountViewModel> =
            companyInfo.companies.map { company -> AccountViewModel.Company(name = company.name) }

        onView { it.show(viewModels) }
    }

    private fun showStudentInfo(studentInfo: AccountInfo.Student) {
        onView {
            it.show(arrayListOf(
                AccountViewModel.Student(
                    name = "${studentInfo.firstName} ${studentInfo.lastName}",
                    qrCode = studentInfo.qrCode
                ))
            )
        }
    }
}