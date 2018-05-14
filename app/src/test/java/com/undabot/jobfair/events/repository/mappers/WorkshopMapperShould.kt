package com.undabot.jobfair.events.repository.mappers

import WorkshopsQuery
import com.nhaarman.mockito_kotlin.isNull
import com.nhaarman.mockito_kotlin.whenever
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.equals
import com.undabot.jobfair.events.entities.Event
import com.undabot.jobfair.events.entities.Workshop
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WorkshopMapperShould {

    private lateinit var mapper: WorkshopMapper

    private lateinit var workshopResource: WorkshopsQuery.Workshop
    private lateinit var workshop: Workshop
    @Mock private lateinit var companyMapper: WorkshopsCompanyMapper
    @Mock private var companyResource: WorkshopsQuery.Company? = null
    @Mock private lateinit var startDateTime: DateTime
    @Mock private lateinit var endDateTime: DateTime
    @Mock private lateinit var expectedCompany: Company

    @Before
    fun prepare() {
        mapper = WorkshopMapper(companyMapper)
        `company mapper result`()
    }

    @Test
    fun `map non-null resource id to workshop id `() {
        Given { workshopResource = workshopResource(id = "1") }
        When { `map is requested`() }
        Then { workshop.id equals "1" }
    }

    @Test
    fun `map null resource id to empty workshop id`() {
        Given { workshopResource = workshopResource(id = null) }
        When { `map is requested`() }
        Then { workshop.id equals "" }
    }

    @Test
    fun `map non-null resource name to workshop title`() {
        Given { workshopResource = workshopResource(name = "Title") }
        When { `map is requested`() }
        Then { workshop.title equals "Title" }
    }

    @Test
    fun `map null resource name to empty workshop title`() {
        Given { workshopResource = workshopResource(name = null) }
        When { `map is requested`() }
        Then { workshop.title equals "" }
    }

    @Test
    fun `map non-null resource description to workshop description`() {
        Given { workshopResource = workshopResource(description = "Description") }
        When { `map is requested`() }
        Then { workshop.description equals "Description" }
    }

    @Test
    fun `map null resource description to empty workshop description`() {
        Given { workshopResource = workshopResource(description = null) }
        When { `map is requested`() }
        Then { workshop.description equals "" }
    }

    @Test
    fun `map non-null resource location to workshop resource`() {
        Given { workshopResource = workshopResource(location = "Location") }
        When { `map is requested`() }
        Then { workshop.location equals "Location" }
    }

    @Test
    fun `map null resource location to empty workshop location`() {
        Given { workshopResource = workshopResource(location = null) }
        When { `map is requested`() }
        Then { workshop.location equals "" }
    }

    @Test
    fun `map non-null resource start dateTime to workshop start dateTime`() {
        Given { workshopResource = workshopResource(startTime = startDateTime) }
        When { `map is requested`() }
        Then { workshop.startTime equals startDateTime }
    }

    @Test
    fun `map null resource start dateTime to default dateTime`() {
        Given { workshopResource = workshopResource(startTime = null) }
        When { `map is requested`() }
        Then { workshop.startTime equals Event.DEFAULT_DATE_TIME }
    }

    @Test
    fun `map non-null resource end dateTime to workshop end dateTime`() {
        Given { workshopResource = workshopResource(endTime = endDateTime) }
        When { `map is requested`() }
        Then { workshop.endTime equals endDateTime }
    }

    @Test
    fun `map null resource end dateTime to default dateTime`() {
        Given { workshopResource = workshopResource(endTime = null) }
        When { `map is requested`() }
        Then { workshop.endTime equals Event.DEFAULT_DATE_TIME }
    }

    @Test
    fun `map resource company to workshop company`() {
        Given { workshopResource = workshopResource(company = companyResource) }
        When { `map is requested`() }
        Then { workshop.company equals expectedCompany }
    }

    private fun `map is requested`() {
        workshop = mapper.map(workshopResource)
    }

    private fun `company mapper result`() {
        whenever(companyMapper.map(isNull())).thenReturn(Company())
        whenever(companyMapper.map(companyResource)).thenReturn(expectedCompany)
    }

    private fun workshopResource(
        id: String? = "",
        name: String? = "",
        description: String? = "",
        startTime: DateTime? = DateTime(),
        endTime: DateTime? = DateTime(),
        location: String? = "",
        company: WorkshopsQuery.Company? = null
    ) = WorkshopsQuery.Workshop(
            "",
            id,
            name,
            startTime,
            endTime,
            description,
            location,
            company
    )
}