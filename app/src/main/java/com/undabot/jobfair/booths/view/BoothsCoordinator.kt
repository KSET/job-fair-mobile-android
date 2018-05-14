package com.undabot.jobfair.booths.view

import com.undabot.jobfair.booths.usecases.ShowBooths
import com.undabot.jobfair.booths.view.BoothsContract.Coordinator
import com.undabot.jobfair.booths.view.BoothsContract.Presenter
import com.undabot.jobfair.booths.view.BoothsContract.View
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.core.view.AbsCoordinator
import javax.inject.Inject

class BoothsCoordinator
@Inject constructor(
        presenter: Presenter,
        private val showBooths: ShowBooths
) : AbsCoordinator<View, Presenter>(presenter), Coordinator {

    override fun onMapReady() = showBooths.with(presenter)

    override fun onCompanyPressed(company: Company) = presenter.showCompanyDetails(company)
}