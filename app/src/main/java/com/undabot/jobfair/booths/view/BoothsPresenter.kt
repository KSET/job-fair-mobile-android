package com.undabot.jobfair.booths.view

import com.undabot.jobfair.booths.entities.Booths
import com.undabot.jobfair.booths.view.BoothsContract.Presenter
import com.undabot.jobfair.booths.view.BoothsContract.View
import com.undabot.jobfair.booths.view.mappers.BoothsViewModelMapper
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.core.view.AbsPresenter
import javax.inject.Inject

class BoothsPresenter
@Inject constructor(
        private val viewModelMapper: BoothsViewModelMapper
) : AbsPresenter<View>(), Presenter {

    override fun boothsLoadingInProgress() = onView { it.showLoading() }

    override fun boothsLoadingErrorWith(errorMessage: String) = onView { it.showGeneralError() }

    override fun boothsLoaded(booths: Booths) =
            onView { it.showBoothsWith(viewModelMapper.map(booths)) }

    override fun showCompanyDetails(company: Company) = onView { it.showCompanyDetails(company) }
}