package com.undabot.jobfair.companies.list.view

import com.nhaarman.mockito_kotlin.verify
import com.undabot.jobfair.And
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.companies.entities.Industry
import com.undabot.jobfair.companies.view.mappers.CompanyViewModelMapper
import com.undabot.jobfair.companies.view.mappers.IndustryItemMapper
import com.undabot.jobfair.companies.view.models.CompanyViewModel
import com.undabot.jobfair.companies.view.models.IndustryViewModel
import com.undabot.jobfair.core.entities.Image
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CompaniesPresenterShould {

    private lateinit var presenter: CompaniesContract.Presenter

    private val id = "id"
    private val logo = Image(small = "url")
    private val name = "name"
    private val companies: List<Company> = listOf(Company(id, logo, name))
    private val industryId = "id"
    private val industryName = "name"
    private val industry = Industry(industryId, industryName, listOf(id))
    private val industries: List<Industry> = listOf(industry)

    @Mock lateinit var view: CompaniesContract.View

    @Before
    fun prepare() {
        presenter = CompaniesPresenter(CompanyViewModelMapper(), IndustryItemMapper())
        presenter.bind(view)
    }

    @Test
    fun `display filtered industries`() {
        Given { presenter And { companies } }
        When { presenter.displayFilteredCompanies(companies) }
        Then {
            verify(view).displayCompanies(listOf(CompanyViewModel(id, logo.small, name)))
            verify(view).displayReady()
        }
    }

    @Test
    fun `display list of industries`() {
        Given { presenter And { industries } }
        When { presenter.displayIndustries(industries) }
        Then { verify(view).prepareIndustries(listOf(IndustryViewModel(industryId, industryName, listOf(id)))) }
    }

    @Test
    fun `display empty if no companies in that industry`() {
        Given { presenter }
        When { presenter.displayEmpty() }
        Then {
            verify(view).displayReady()
            verify(view).displayEmpty()
        }
    }

    @Test
    fun `display an error on the view when error occurs`() {
        Given { presenter }
        When { presenter.displayError() }
        Then {
            verify(view).displayReady()
            verify(view).displayError()
        }
    }

    @Test
    fun `display a list of companies`() {
        Given { presenter And { companies } }
        When { presenter.displayCompanies(companies) }
        Then {
            verify(view).displayCompanies(
                    listOf(CompanyViewModel(id, logo.small, name)))
            verify(view).displayReady()
        }
    }
}