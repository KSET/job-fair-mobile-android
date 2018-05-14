package com.undabot.jobfair.events.calendar

import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.equals
import com.undabot.jobfair.events.entities.Event
import com.undabot.jobfair.events.entities.Presentation
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CalendarInfoMapperShould {

    private lateinit var mapper: CalendarInfoMapper
    private lateinit var event: Event
    private lateinit var calendarInfo: CalendarInfo
    @Mock private lateinit var startTime: DateTime
    @Mock private lateinit var endTime: DateTime

    @Before
    fun prepare() {
        mapper = CalendarInfoMapper()
    }

    @Test
    fun `map company title and event name to calendar data title`() {
        Given { event = Presentation(title = "Presentation title", company = Company(name = "Company name")) }
        When { `map is requested`() }
        Then { calendarInfo.title equals "Company name - Presentation title" }
    }

    @Test
    fun `map event start time to calendar start time`() {
        Given { event = Presentation(startTime = startTime) }
        When { `map is requested`() }
        Then { calendarInfo.startTime equals startTime }
    }

    @Test
    fun `map event end time to calendar event time`() {
        Given { event = Presentation(endTime = endTime) }
        When { `map is requested`() }
        Then { calendarInfo.endTime equals endTime }
    }

    @Test
    fun `map event location name to calendar location name`() {
        Given { event = Presentation(location = "D1") }
        When { `map is requested`() }
        Then { calendarInfo.locationName equals "D1" }
    }

    private fun `map is requested`() {
        calendarInfo = mapper.map(event)
    }
}
