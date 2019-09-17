package com.undabot.jobfair.booths.usecases

import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.undabot.jobfair.Given
import com.undabot.jobfair.TestSchedulerProvider
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.booths.entities.Booth
import com.undabot.jobfair.booths.entities.Booths
import com.undabot.jobfair.booths.repository.BoothsRepository
import com.undabot.jobfair.booths.repository.BoothsResult
import com.undabot.jobfair.core.schedulers.MainScheduler
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ShowBoothsShould {

    private lateinit var showBooths: ShowBooths

    @Mock private lateinit var presenter: ShowBooths.ShowsBooths
    @Mock private lateinit var boothsRepository: BoothsRepository
    @Mock private lateinit var boothsList: List<Booth>
    private lateinit var booths: Booths
    private lateinit var boothsResult: BoothsResult
    private val mainScheduler: MainScheduler = TestSchedulerProvider.mainScheduler()
    private val errorMessage = "Error message"

    @Before
    fun prepare() {
        showBooths = ShowBooths(boothsRepository, mainScheduler)
        booths = Booths(boothsList)
        boothsResult = BoothsResult(boothsList)
    }

    @Test
    fun `notify presenter when booths loading is in progress`() {
        Given { `repository can provide booths`() }
        When { `show booths is requested`() }
        Then { verify(presenter).boothsLoadingInProgress() }
    }

    @Test
    fun `notify presenter when booths are loaded`() {
        Given { `repository can provide booths`() }
        When { `show booths is requested`() }
        Then { verify(presenter).boothsLoaded(booths) }
    }

    @Test
    fun `notify presenter when booths can't be loaded`() {
        Given { `repository can't provide booths`() }
        When { `show booths is requested`() }
        Then { verify(presenter).boothsLoadingErrorWith(errorMessage) }
    }

    private fun `repository can't provide booths`() {
        whenever(boothsRepository.booths()).thenReturn(Single.error(Throwable(errorMessage)))
    }

    private fun `repository can provide booths`() {
        whenever(boothsRepository.booths()).thenReturn(Single.just(boothsResult))
    }

    private fun `show booths is requested`() {
        showBooths.with(presenter)
    }
}