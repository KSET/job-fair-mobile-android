package com.undabot.jobfair.companies.list.usecases

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.undabot.jobfair.And
import com.undabot.jobfair.Given
import com.undabot.jobfair.TestSchedulerProvider
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.companies.entities.Industry
import com.undabot.jobfair.companies.list.view.CompaniesContract
import com.undabot.jobfair.companies.repository.CompaniesRepository
import com.undabot.jobfair.core.entities.Image
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FilterCompaniesByIndustryShould {

    private lateinit var useCase: FilterCompaniesByIndustry

    @Mock lateinit var presenter: CompaniesContract.Presenter
    @Mock private lateinit var repository: CompaniesRepository
    private val filterableCompany = Company("1", Image(), "Test 1")
    private val nonfilterableCompany = Company("2", Image(), "Test 2")
    private val companyIdList = listOf("2")
    private val industry = Industry("1", "Test", companyIdList)

    @Before
    fun prepare() {
        useCase = FilterCompaniesByIndustry(repository, TestSchedulerProvider.mainScheduler())
    }

    @Test
    fun `request companies from repository`() {
        Given { useCase } And { `repository can provide companies`() }
        When { useCase.filter(industry, presenter) }
        Then { verify(repository).fetchCompanies() }
    }

    @Test
    fun `filter companies by industry id`() {
        Given { useCase } And { `repository can provide companies`() }
        When { useCase.filter(industry, presenter) }
        Then { verify(presenter).displayFilteredCompanies(listOf(nonfilterableCompany)) }
    }

    @Test
    fun `pass error to presenter when repository returns an error`() {
        Given { useCase And { `repostory can't provide companies`() } }
        When { useCase.filter(industry, presenter) }
        Then { verify(presenter).displayError() }
    }

    private fun `repository can provide companies`() {
        whenever(repository.fetchCompanies()).thenReturn(
                Single.just(listOf(filterableCompany, nonfilterableCompany)))
    }

    private fun `repostory can't provide companies`() {
        whenever(repository.fetchCompanies()).thenReturn(Single.error(Error()))
    }
}