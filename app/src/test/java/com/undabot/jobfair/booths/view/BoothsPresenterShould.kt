package com.undabot.jobfair.booths.view

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.booths.entities.Booths
import com.undabot.jobfair.booths.view.mappers.BoothsViewModelMapper
import com.undabot.jobfair.booths.view.models.BoothsViewModel
import com.undabot.jobfair.companies.entities.Company
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BoothsPresenterShould {

    private lateinit var presenter: BoothsContract.Presenter

    @Mock private lateinit var view: BoothsContract.View
    @Mock private lateinit var booths: Booths
    @Mock private lateinit var boothsViewModel: BoothsViewModel
    @Mock private lateinit var viewModelMapper: BoothsViewModelMapper
    @Mock private lateinit var company: Company

    @Before
    fun prepare() {
        presenter = BoothsPresenter(viewModelMapper)
        presenter.bind(view)
        whenever(viewModelMapper.map(booths)).thenReturn(boothsViewModel)
    }

    @Test
    fun `show loading view when booths loading is in progress`() {
        When { presenter.boothsLoadingInProgress() }
        Then { verify(view).showLoading() }
    }

    @Test
    fun `show general error message when booths loading fails`() {
        When { presenter.boothsLoadingErrorWith("message") }
        Then { verify(view).showGeneralError() }
    }

    @Test
    fun `show booths when booths are loaded`() {
        When { presenter.boothsLoaded(booths) }
        Then { verify(view).showBoothsWith(boothsViewModel) }
    }

    @Test
    fun `open company details when requested`() {
        When { presenter.showCompanyDetails(company) }
        Then { verify(view).showCompanyDetails(company) }
    }
}