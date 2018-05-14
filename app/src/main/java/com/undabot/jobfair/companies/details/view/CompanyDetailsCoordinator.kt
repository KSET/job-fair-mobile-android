package com.undabot.jobfair.companies.details.view

import com.undabot.jobfair.companies.details.usecases.ShowCompany
import com.undabot.jobfair.core.view.AbsCoordinator
import javax.inject.Inject

class CompanyDetailsCoordinator
@Inject
constructor(
        private val showCompany: ShowCompany,
        presenter: CompanyDetails.Presenter
) : AbsCoordinator<CompanyDetails.View, CompanyDetails.Presenter>(presenter),
        CompanyDetails.Coordinator {

    override fun companyShown(companyID: String) = showCompany.forId(companyID, presenter)
}