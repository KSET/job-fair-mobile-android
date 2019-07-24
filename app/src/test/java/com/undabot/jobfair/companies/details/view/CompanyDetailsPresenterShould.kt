package com.undabot.jobfair.companies.details.view

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.details.mappers.CompanyDetailsMapper
import com.undabot.jobfair.companies.details.view.models.CompanyDetailsViewModel
import com.undabot.jobfair.companies.entities.DetailedCompany
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CompanyDetailsPresenterShould {

    lateinit var presenter: CompanyDetails.Presenter
    @Mock
    private lateinit var view: CompanyDetails.View
    @Mock
    private lateinit var mapper: CompanyDetailsMapper
    @Mock
    private lateinit var companyDetailsViewModel: CompanyDetailsViewModel

    @Mock
    lateinit var company: DetailedCompany

    @Before
    fun prepare() {
        presenter = CompanyDetailsPresenter(mapper)
        presenter.bind(view)
        whenever(mapper.map(company)).thenReturn(companyDetailsViewModel)
    }

    @Test
    fun `display company details when they are received`() {
        Given { presenter }
        When { presenter.displayDetails(company) }
        Then { verify(view).displayCompanyDetails(companyDetailsViewModel) }
    }

    @Test
    fun `display error on view when received`() {
        Given { presenter }
        When { presenter.displayError() }
        Then { verify(view).displayError() }
    }
}