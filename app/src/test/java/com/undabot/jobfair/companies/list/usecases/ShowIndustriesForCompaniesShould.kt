package com.undabot.jobfair.companies.list.usecases

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.undabot.jobfair.And
import com.undabot.jobfair.Given
import com.undabot.jobfair.TestSchedulerProvider
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.entities.Industry
import com.undabot.jobfair.companies.list.view.CompaniesContract
import com.undabot.jobfair.companies.repository.CompaniesRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ShowIndustriesForCompaniesShould {

    private lateinit var useCase: ShowIndustriesForCompanies

    @Mock lateinit var presenter: CompaniesContract.Presenter
    @Mock private lateinit var repository: CompaniesRepository
    @Mock lateinit var industry: Industry

    @Before
    fun prepare() {
        useCase = ShowIndustriesForCompanies(repository, TestSchedulerProvider.mainScheduler())
    }

    @Test
    fun `request industries from repository`() {
        Given { useCase } And { `repository can provide industries`() }
        When { useCase.fetch(presenter) }
        Then { verify(repository).fetchIndustries() }
    }

    @Test
    fun `pass industries to presenter when repository returns them`() {
        Given { useCase } And { `repository can provide industries`() }
        When { useCase.fetch(presenter) }
        Then { verify(presenter).displayIndustries(listOf(industry)) }
    }

    @Test
    fun `pass error to presenter when repository returns an error`() {
        Given { useCase } And { `repository can't provide industries`() }
        When { useCase.fetch(presenter) }
        Then { verify(presenter).displayError() }
    }

    private fun `repository can provide industries`() {
        whenever(repository.fetchIndustries()).thenReturn(Single.just(listOf(industry)))
    }

    private fun `repository can't provide industries`() {
        whenever(repository.fetchIndustries()).thenReturn(Single.error(Error()))
    }
}