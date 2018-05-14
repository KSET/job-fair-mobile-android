package com.undabot.jobfair.events.view.mappers

import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import com.undabot.jobfair.And
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.core.entities.Image
import com.undabot.jobfair.equals
import com.undabot.jobfair.events.calendar.CalendarInfo
import com.undabot.jobfair.events.calendar.CalendarInfoMapper
import com.undabot.jobfair.events.entities.Event
import com.undabot.jobfair.events.entities.Presentation
import com.undabot.jobfair.events.entities.Workshop
import com.undabot.jobfair.events.view.EventViewModel
import com.undabot.jobfair.utils.DateTimeComparator
import com.undabot.jobfair.utils.DateTimeFormatter
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class EventViewModelMapperShould {

    private lateinit var mapper: EventViewModelMapper

    @Mock private lateinit var startDateTime: DateTime
    @Mock private lateinit var endDateTime: DateTime
    @Mock private lateinit var dateTimeFormatter: DateTimeFormatter
    @Mock private lateinit var dateTimeComparator: DateTimeComparator
    @Mock private lateinit var calendarInfoMapper: CalendarInfoMapper
    @Mock private lateinit var calendarInfo: CalendarInfo

    private lateinit var actualViewModel: EventViewModel
    private var event: Event = Presentation()
    private val formattedStartDateTime = "May 14, 09:30 AM"
    private val formattedEndDateTime = "May 14, 10:30 AM"
    private val formattedFullTime = "May 14, 10:30 - 11:00"

    @Before
    fun prepare() {
        mapper = EventViewModelMapper(dateTimeFormatter, dateTimeComparator, calendarInfoMapper)
        `date time result`()
        `calendar mapper result`()
    }

    @Test
    fun `map presentation id to view model id`() {
        Given { event = Presentation(id = "1") }
        When { `map is requested`() }
        Then { actualViewModel.id equals "1" }
    }

    @Test
    fun `map presentation title to view model title`() {
        Given { event = Presentation(title = "Presentation title") }
        When { `map is requested`() }
        Then { actualViewModel.title equals "Presentation title" }
    }

    @Test
    fun `map presentation start date time as formatted date time to view model`() {
        Given { event = Presentation(startTime = startDateTime) }
        When { `map is requested`() }
        Then { actualViewModel.startTime equals formattedStartDateTime }
    }

    @Test
    fun `map presentation end date time as formatted date time to view model`() {
        Given { event = Presentation(endTime = endDateTime) }
        When { `map is requested`() }
        Then { actualViewModel.endTime equals formattedEndDateTime }
    }

    @Test
    fun `map presenter image thumbnail from presentation to view model presenters image`() {
        Given { event = Presentation(presentersImage = Image(thumb = "imageUrl")) }
        When { `map is requested`() }
        Then { actualViewModel.presentersImage equals "imageUrl" }
    }

    @Test
    fun `map presenter bio from presentation to view model`() {
        Given { event = Presentation(presentersBio = "presenter's biography") }
        When { `map is requested`() }
        Then { actualViewModel.presentersBio equals "presenter's biography" }
    }

    @Test
    fun `set presenters bio empty when event is workshop`() {
        Given { event = Workshop("10") }
        When { `map is requested`() }
        Then { actualViewModel.presentersBio equals "" }
    }

    @Test
    fun `set presenters image empty when event is workshop`() {
        Given { event = Workshop("10") }
        When { `map is requested`() }
        Then { actualViewModel.presentersImage equals "" }
    }

    @Test
    fun `map company id from presentations company to view model`() {
        Given { event = Presentation(company = Company(id = "2")) }
        When { `map is requested`() }
        Then { actualViewModel.companyId equals "2" }
    }

    @Test
    fun `map large company image from presentations company to view model company image`() {
        Given { event = Presentation(company = Company(image = Image(large = "imageUrl"))) }
        When { `map is requested`() }
        Then { actualViewModel.companyImage equals "imageUrl" }
    }

    @Test
    fun `map event to calendar data in view model`() {
        Given { event = Presentation(title = "Title") } And { `calendar mapper result`() }
        When { `map is requested`() }
        Then { actualViewModel.calendarInfo equals calendarInfo }
    }

    @Test
    fun `map currently in progress as true when presentations is live`() {
        Given { event = Presentation(startTime = startDateTime, endTime = endDateTime) }
        And { `presentation is currently in progress`() }
        When { `map is requested`() }
        Then { actualViewModel.currentlyInProgress equals true }
    }

    @Test
    fun `map currently in progress as false when presentation is not live`() {
        Given { event = Presentation(startTime = startDateTime, endTime = endDateTime) }
        And { `presentation is not currently in progress`() }
        When { `map is requested`() }
        Then { actualViewModel.currentlyInProgress equals false }
    }

    @Test
    fun `map start time and end time as full time to view model`() {
        Given { event = Presentation(startTime = startDateTime, endTime = endDateTime) }
        When { `map is requested`() }
        Then { actualViewModel.fullTime equals formattedFullTime }
    }

    @Test
    fun `map presentation description to view model description`() {
        Given { event = Presentation(description = "Presentation description") }
        When { `map is requested`() }
        Then { actualViewModel.description equals "Presentation description" }
    }

    @Test
    fun `map presentation location to view model presentation`() {
        Given { event = Presentation(location = "Hall D2") }
        When { `map is requested`() }
        Then { actualViewModel.location equals "Hall D2" }
    }

    @Test
    fun `map presentation company name to view model company name`() {
        Given { event = Presentation(company = Company(name = "Company name")) }
        When { `map is requested`() }
        Then { actualViewModel.companyName equals "Company name" }
    }

    @Test
    fun `map is in past to true when date is in past`() {
        Given { event = Presentation(startTime = startDateTime) }
        And { `event is in the past`() }
        When { `map is requested`() }
        Then { actualViewModel.inPast equals true }
    }

    @Test
    fun `map is in past to false when date is in the future`() {
        Given { event = Presentation(startTime = startDateTime) }
        And { `event is in the future`() }
        When { `map is requested`() }
        Then { actualViewModel.inPast equals false }
    }

    private fun `event is in the future`() {
        whenever(dateTimeComparator.isInPast(startDateTime)).thenReturn(false)
    }

    private fun `event is in the past`() {
        whenever(dateTimeComparator.isInPast(startDateTime)).thenReturn(true)
    }

    private fun `presentation is currently in progress`() {
        whenever(dateTimeComparator.isNow(startDateTime, endDateTime)).thenReturn(true)
    }

    private fun `presentation is not currently in progress`() {
        whenever(dateTimeComparator.isNow(startDateTime, endDateTime)).thenReturn(false)
    }

    private fun `map is requested`() {
        actualViewModel = mapper.map(event)
    }

    private fun `calendar mapper result`() {
        whenever(calendarInfoMapper.map(any())).thenReturn(CalendarInfo())
        whenever(calendarInfoMapper.map(event)).thenReturn(calendarInfo)
    }

    private fun `date time result`() {
        dateTimeFormatter(any(), "")
        dateTimeFormatter(startDateTime, formattedStartDateTime)
        dateTimeFormatter(endDateTime, formattedEndDateTime)
        whenever(dateTimeFormatter.formatToFullDateTime(any(), any())).thenReturn("")
        whenever(dateTimeFormatter.formatToFullDateTime(startDateTime, endDateTime)).thenReturn(formattedFullTime)
    }

    private fun dateTimeFormatter(matcher: DateTime, replaceWith: String) {
        whenever(dateTimeFormatter.formatToDateAndTime(matcher)).thenReturn(replaceWith)
    }
}