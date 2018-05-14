package com.undabot.jobfair.booths.view

import com.undabot.jobfair.booths.usecases.ShowBooths
import com.undabot.jobfair.booths.view.models.BoothsViewModel
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.core.view.BaseCoordinator
import com.undabot.jobfair.core.view.BasePresenter

interface BoothsContract {

    interface Coordinator : BaseCoordinator<View> {
        fun onMapReady()
        fun onCompanyPressed(company: Company)
    }

    interface Presenter : BasePresenter<View>,
            ShowBooths.ShowsBooths {
        fun showCompanyDetails(company: Company)
    }

    interface View {
        fun showLoading()
        fun showGeneralError()
        fun showBoothsWith(boothsViewModel: BoothsViewModel)
        fun showCompanyDetails(company: Company)
    }
}