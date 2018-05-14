package com.undabot.jobfair.companies.details.view

import com.nhaarman.mockito_kotlin.verify
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.details.usecases.ShowCompany
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CompanyDetailsCoordinatorShould {

    private lateinit var coordinator: CompanyDetails.Coordinator

    @Mock private lateinit var useCase: ShowCompany
    @Mock private lateinit var presenter: CompanyDetailsPresenter
    private val testId = "ID"

    @Before
    fun prepare() {
        coordinator = CompanyDetailsCoordinator(useCase, presenter)
    }

    @Test
    fun `request company details when a company is shown`() {
        Given { coordinator }
        When { coordinator.companyShown(testId) }
        Then { verify(useCase).forId(testId, presenter) }
    }
}
