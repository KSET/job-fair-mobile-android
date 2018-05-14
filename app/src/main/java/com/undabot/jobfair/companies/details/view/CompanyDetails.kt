package com.undabot.jobfair.companies.details.view

import com.undabot.jobfair.companies.details.usecases.ShowCompany
import com.undabot.jobfair.companies.details.view.models.CompanyDetailsViewModel
import com.undabot.jobfair.core.view.BaseCoordinator
import com.undabot.jobfair.core.view.BasePresenter

interface CompanyDetails {

    interface View {
        fun displayCompanyDetails(viewModel: CompanyDetailsViewModel)
        fun displayError()
    }

    interface Presenter : BasePresenter<View>, ShowCompany.DisplaysCompanyDetail

    interface Coordinator : BaseCoordinator<View> {
        fun companyShown(companyID: String)
    }
}