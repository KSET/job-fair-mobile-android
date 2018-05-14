package com.undabot.jobfair.events.repository

import PresentationsQuery
import WorkshopsQuery
import com.nhaarman.mockito_kotlin.whenever
import com.undabot.jobfair.Given
import com.undabot.jobfair.TestSchedulerProvider
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.core.schedulers.WorkerScheduler
import com.undabot.jobfair.equals
import com.undabot.jobfair.events.entities.Event
import com.undabot.jobfair.events.entities.Presentation
import com.undabot.jobfair.events.entities.Workshop
import com.undabot.jobfair.events.repository.mappers.PresentationMapper
import com.undabot.jobfair.events.repository.mappers.WorkshopMapper
import com.undabot.jobfair.networking.services.ResourceApiService
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EventsRepositoryShould {

    private lateinit var repository: EventsRepository
    @Mock private lateinit var resourceApiService: ResourceApiService
    @Mock private lateinit var presentationsMapper: PresentationMapper
    @Mock private lateinit var workshopsMapper: WorkshopMapper
    @Mock private lateinit var servicePresentation1: PresentationsQuery.Presentation
    @Mock private lateinit var servicePresentation2: PresentationsQuery.Presentation
    @Mock private lateinit var serviceWorkshop1: WorkshopsQuery.Workshop
    @Mock private lateinit var serviceWorkshop2: WorkshopsQuery.Workshop
    @Mock private lateinit var presentation1: Presentation
    @Mock private lateinit var presentation2: Presentation
    @Mock private lateinit var workshop1: Workshop
    @Mock private lateinit var workshop2: Workshop
    private lateinit var presentationsServiceResponse: List<PresentationsQuery.Presentation>
    private lateinit var workshopsServiceResponse: List<WorkshopsQuery.Workshop>
    private lateinit var actualResult: EventResult
    private val workerScheduler: WorkerScheduler = TestSchedulerProvider.workerScheduler()

    @Test
    fun `return presentations result when presentations are requested`() {
        Given { `repository for presentations type`() }
        When { `presentations are requested`() }
        Then { actualResult equals EventResult(arrayListOf(presentation1, presentation2)) }
    }

    @Test
    fun `return workshops when events are requested and provided type is workshops`() {
        Given { `repository for workshops type`() }
        When { `presentations are requested`() }
        Then { actualResult equals EventResult(arrayListOf(workshop1, workshop2)) }
    }

    private fun `repository for presentations type`() {
        repository = EventsRepositoryImpl(Event.Type.PRESENTATION, resourceApiService,
                presentationsMapper, workshopsMapper,
                workerScheduler)
        presentationsServiceResponse = arrayListOf(servicePresentation1, servicePresentation2)
        whenever(resourceApiService.presentations()).thenReturn(Single.just(presentationsServiceResponse))
        whenever(presentationsMapper.map(servicePresentation1)).thenReturn(presentation1)
        whenever(presentationsMapper.map(servicePresentation2)).thenReturn(presentation2)
    }

    private fun `repository for workshops type`() {
        repository = EventsRepositoryImpl(Event.Type.WORKSHOP, resourceApiService,
                presentationsMapper, workshopsMapper,
                workerScheduler)
        workshopsServiceResponse = arrayListOf(serviceWorkshop1, serviceWorkshop2)
        whenever(resourceApiService.workshops()).thenReturn(Single.just(workshopsServiceResponse))
        whenever(workshopsMapper.map(serviceWorkshop1)).thenReturn(workshop1)
        whenever(workshopsMapper.map(serviceWorkshop2)).thenReturn(workshop2)
    }

    private fun `presentations are requested`() {
        actualResult = repository.getEvents().blockingGet()
    }
}