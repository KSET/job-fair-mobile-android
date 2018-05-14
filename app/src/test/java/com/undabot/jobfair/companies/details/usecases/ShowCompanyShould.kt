package com.undabot.jobfair.companies.details.usecases

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.undabot.jobfair.And
import com.undabot.jobfair.Given
import com.undabot.jobfair.TestSchedulerProvider
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.details.view.CompanyDetails
import com.undabot.jobfair.companies.entities.DetailedCompany
import com.undabot.jobfair.companies.repository.CompaniesRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ShowCompanyShould {

    private lateinit var useCase: ShowCompany

    @Mock
    lateinit var presenter: CompanyDetails.Presenter
    @Mock
    private lateinit var repository: CompaniesRepository

    @Mock
    lateinit var company: DetailedCompany

    private val testString = "test"

    @Before
    fun prepare() {
        useCase = ShowCompany(repository, TestSchedulerProvider.mainScheduler())
        whenever(repository.fetchDetailsForCompany(testString)).thenReturn(Single.just(company))
    }

    @Test
    fun `request company from repository`() {
        Given { useCase }
        When { useCase.forId(testString, presenter) }
        Then { verify(repository).fetchDetailsForCompany(testString) }
    }

    @Test
    fun `pass company to presenter when repository returns them`() {
        Given { useCase }
        When { useCase.forId(testString, presenter) }
        Then { verify(presenter).displayDetails(company) }
    }

    @Test
    fun `pass error to presenter when repository returns an error`() {
        Given {
            useCase And {
                whenever(repository.fetchDetailsForCompany(testString))
                        .thenReturn(Single.error(Error()))
            }
        }
        When { useCase.forId(testString, presenter) }
        Then { verify(presenter).displayError() }
    }
}