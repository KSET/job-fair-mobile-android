package com.undabot.jobfair.companies.list.usecases

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.undabot.jobfair.And
import com.undabot.jobfair.Given
import com.undabot.jobfair.TestSchedulerProvider
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.entities.Company
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
class ShowCompaniesShould {

    private lateinit var useCase: ShowCompanies

    @Mock lateinit var presenter: CompaniesContract.Presenter
    @Mock private lateinit var repository: CompaniesRepository
    private val companies = Company("test", Image(), "test")

    @Before
    fun prepare() {
        useCase = ShowCompanies(repository, TestSchedulerProvider.mainScheduler())
    }

    @Test
    fun `request industries from repository`() {
        Given { useCase } And { `repository can provide companies`() }
        When { useCase.fetch(presenter) }
        Then { verify(repository).fetchCompanies() }
    }

    @Test
    fun `pass companies to presenter when repository returns them`() {
        Given { useCase } And { `repository can provide companies`() }
        When { useCase.fetch(presenter) }
        Then { verify(presenter).displayCompanies(listOf(companies)) }
    }

    @Test
    fun `pass error to presenter when repository returns an error`() {
        Given { useCase } And { `repository can't provide companies`() }
        When { useCase.fetch(presenter) }
        Then { verify(presenter).displayError() }
    }

    private fun `repository can't provide companies`() {
        whenever(repository.fetchCompanies()).thenReturn(Single.error(Error()))
    }

    private fun `repository can provide companies`() {
        whenever(repository.fetchCompanies()).thenReturn(Single.just(listOf(companies)))
    }
}