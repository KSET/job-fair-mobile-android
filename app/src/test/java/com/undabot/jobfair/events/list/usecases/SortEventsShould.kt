package com.undabot.jobfair.events.list.usecases

import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.equals
import com.undabot.jobfair.events.entities.Events
import com.undabot.jobfair.events.entities.Presentation
import com.undabot.jobfair.events.repository.EventResult
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test

class SortEventsShould {

    private lateinit var sortEvents: SortEvents

    private lateinit var actualEvents: Events
    private lateinit var expectedFirstList: List<Presentation>
    private lateinit var expectedSecondList: List<Presentation>
    private lateinit var eventResult: EventResult

    private val firstDayFirstTime = presentationForDate("2017-05-17T09:30:00.0000000+03:00")
    private val firstDaySecondTime = presentationForDate("2017-05-17T10:00:00.0000000+03:00")
    private val firstDayThirdTime = presentationForDate("2017-05-17T10:30:00.0000000+03:00")
    private val firstDayForthTime = presentationForDate("2017-05-17T11:00:00.0000000+03:00")
    private val secondDayFirstTime = presentationForDate("2017-05-18T09:30:00.0000000+03:00")
    private val secondDaySecondTime = presentationForDate("2017-05-18T10:00:00.0000000+03:00")
    private val secondDayThirdTime = presentationForDate("2017-05-18T10:30:00.0000000+03:00")
    private val secondDayForthTime = presentationForDate("2017-05-18T11:00:00.0000000+03:00")
    private val thirdDayFirstTime = presentationForDate("2017-05-19T11:00:00.0000000+03:00")
    private val thirdDaySecondTime = presentationForDate("2017-05-19T11:30:00.0000000+03:00")

    @Before
    fun prepare() {
        sortEvents = SortEvents()
        expectedFirstList = arrayListOf(firstDayFirstTime, firstDaySecondTime, firstDayThirdTime, firstDayForthTime)
        expectedSecondList = arrayListOf(secondDayFirstTime, secondDaySecondTime, secondDayThirdTime, secondDayForthTime)
    }

    @Test
    fun `sort presentations from the first day to the first list`() {
        Given { `presentations result`() }
        When { `sort is requested`() }
        Then { actualEvents.firstDay equals expectedFirstList }
    }

    @Test
    fun `sort presentations from the second day to second list`() {
        Given { `presentations result`() }
        When { `sort is requested`() }
        Then { actualEvents.secondDay equals expectedSecondList }
    }

    @Test
    fun `ignore presentations that do not belong to first or second day`() {
        Given { `presentations result`() }
        When { `sort is requested`() }
        Then { actualEvents equals Events(expectedFirstList, expectedSecondList) }
    }

    @Test
    fun `return empty presentations when list is empty`() {
        Given { eventResult = EventResult() }
        When { `sort is requested`() }
        Then { actualEvents equals Events() }
    }

    private fun `sort is requested`() {
        actualEvents = sortEvents.from(eventResult)
    }

    private fun `presentations result`() {
        eventResult = EventResult(arrayListOf(
                secondDayForthTime,
                firstDaySecondTime,
                secondDayFirstTime,
                secondDayThirdTime,
                firstDayThirdTime,
                thirdDayFirstTime,
                firstDayFirstTime,
                firstDayForthTime,
                thirdDaySecondTime,
                secondDaySecondTime
        ))
    }

    private fun presentationForDate(date: String) = Presentation(startTime = DateTime.parse(date))
}