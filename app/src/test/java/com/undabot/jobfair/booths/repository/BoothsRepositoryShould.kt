package com.undabot.jobfair.booths.repository

import com.nhaarman.mockitokotlin2.whenever
import com.undabot.jobfair.BoothsQuery
import com.undabot.jobfair.Given
import com.undabot.jobfair.TestSchedulerProvider
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.booths.entities.Booth
import com.undabot.jobfair.booths.repository.mappers.BoothMapper
import com.undabot.jobfair.core.schedulers.WorkerScheduler
import com.undabot.jobfair.equals
import com.undabot.jobfair.networking.services.ResourceApiService
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BoothsRepositoryShould {

    private lateinit var repository: BoothsRepository

    @Mock private lateinit var resourceApiService: ResourceApiService
    @Mock private lateinit var boothMapper: BoothMapper
    @Mock private lateinit var boothResource1: BoothsQuery.Booth
    @Mock private lateinit var boothResource2: BoothsQuery.Booth
    @Mock private lateinit var booth1: Booth
    @Mock private lateinit var booth2: Booth
    private lateinit var boothsResult: BoothsResult
    private lateinit var resourceApiServiceResponse: List<BoothsQuery.Booth>
    private val workerScheduler: WorkerScheduler = TestSchedulerProvider.workerScheduler()

    @Before
    fun prepare() {
        repository = BoothsRepositoryImpl(resourceApiService, boothMapper, workerScheduler)
    }

    @Test
    fun `return booths result when booths are requested`() {
        Given { `resource api service`() }
        When { `booths are requested`() }
        Then { boothsResult equals BoothsResult(arrayListOf(booth1, booth2)) }
    }

    private fun `booths are requested`() {
        boothsResult = repository.booths().blockingGet()
    }

    private fun `resource api service`() {
        resourceApiServiceResponse = arrayListOf(boothResource1, boothResource2)
        whenever(resourceApiService.booths()).thenReturn(Single.just(resourceApiServiceResponse))
        whenever(boothMapper.map(boothResource1)).thenReturn(booth1)
        whenever(boothMapper.map(boothResource2)).thenReturn(booth2)
    }
}