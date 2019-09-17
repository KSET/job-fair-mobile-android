package com.undabot.jobfair.booths.view

import com.nhaarman.mockitokotlin2.verify
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.booths.usecases.ShowBooths
import com.undabot.jobfair.companies.entities.Company
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BoothsCoordinatorShould {

    private lateinit var coordinator: BoothsContract.Coordinator

    @Mock private lateinit var view: BoothsContract.View
    @Mock private lateinit var presenter: BoothsContract.Presenter
    @Mock private lateinit var showBooths: ShowBooths
    @Mock private lateinit var company: Company

    @Before
    fun prepare() {
        coordinator = BoothsCoordinator(presenter, showBooths)
        coordinator.bind(view)
    }

    @Test
    fun `show booths when map is ready`() {
        When { coordinator.onMapReady() }
        Then { verify(showBooths).with(presenter) }
    }

    @Test
    fun `open company details when company is pressed`() {
        When { coordinator.onCompanyPressed(company) }
        Then { verify(presenter).showCompanyDetails(company) }
    }
}