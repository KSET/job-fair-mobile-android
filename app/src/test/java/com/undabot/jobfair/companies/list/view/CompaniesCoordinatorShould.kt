package com.undabot.jobfair.companies.list.view

import com.nhaarman.mockito_kotlin.verify
import com.undabot.jobfair.And
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.entities.Industry
import com.undabot.jobfair.companies.list.usecases.FilterCompaniesByIndustry
import com.undabot.jobfair.companies.list.usecases.ShowCompanies
import com.undabot.jobfair.companies.list.usecases.ShowIndustriesForCompanies
import com.undabot.jobfair.companies.view.models.IndustryViewModel
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CompaniesCoordinatorShould {

    private lateinit var coordinator: CompaniesContract.Coordinator

    @Mock private lateinit var companies: ShowCompanies
    @Mock private lateinit var presenter: CompaniesContract.Presenter
    @Mock private lateinit var industriesForCompanies: ShowIndustriesForCompanies
    @Mock private lateinit var filterCompaniesByIndustry: FilterCompaniesByIndustry
    private val industryId = IndustryViewModel("10", "10", listOf())

    @Before
    fun prepare() {
        coordinator = CompaniesCoordinator(presenter, industriesForCompanies,
                companies, filterCompaniesByIndustry)
    }

    @Test
    fun `fetch companies when requested`() {
        Given { coordinator }
        When { coordinator.companiesRequested() }
        Then { verify(companies).fetch(presenter) }
    }

    @Test
    fun `fetch industries when requested`() {
        Given { coordinator }
        When { coordinator.industriesRequested() }
        Then { verify(industriesForCompanies).fetch(presenter) }
    }

    @Test
    fun `filter by industry when requested`() {
        Given { coordinator And { industryId } }
        When { coordinator.requestedFilterBy(industryId) }
        Then {
            verify(filterCompaniesByIndustry)
                    .filter(Industry(industryId.id, industryId.name, industryId.companies),
                            presenter)
        }
    }
}