package com.undabot.jobfair.events.repository.mappers

import PresentationsQuery
import com.nhaarman.mockito_kotlin.isNull
import com.nhaarman.mockito_kotlin.whenever
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.core.entities.Image
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
class PresentationMapperShould {

    private lateinit var mapper: PresentationMapper

    private lateinit var presentationResource: PresentationsQuery.Presentation
    private lateinit var presentation: Presentation
    @Mock private lateinit var expectedPresentersImage: Image
    @Mock private var presentersImageResource: PresentationsQuery.Presenter_photo? = null
    @Mock private lateinit var companyMapper: PresentationsCompanyMapper
    @Mock private lateinit var imageMapper: PresentationPresentersImageMapper
    @Mock private var companyResource: PresentationsQuery.Company? = null
    @Mock private lateinit var startDateTime: DateTime
    @Mock private lateinit var endDateTime: DateTime
    @Mock private lateinit var expectedCompany: Company

    @Before
    fun prepare() {
        mapper = PresentationMapper(companyMapper, imageMapper)
        `company mapper result`()
        `presenters image mapper result`()
    }

    @Test
    fun `map non-null resource id to presentation id `() {
        Given { presentationResource = presentationResource(id = "1") }
        When { `map is requested`() }
        Then { presentation.id equals "1" }
    }

    @Test
    fun `map null resource id to empty presentation id`() {
        Given { presentationResource = presentationResource(id = null) }
        When { `map is requested`() }
        Then { presentation.id equals "" }
    }

    @Test
    fun `map non-null resource title to presentation title`() {
        Given { presentationResource = presentationResource(title = "Title") }
        When { `map is requested`() }
        Then { presentation.title equals "Title" }
    }

    @Test
    fun `map null resource title to empty presentation title`() {
        Given { presentationResource = presentationResource(title = null) }
        When { `map is requested`() }
        Then { presentation.title equals "" }
    }

    @Test
    fun `map non-null resource description to presentation description`() {
        Given { presentationResource = presentationResource(description = "Description") }
        When { `map is requested`() }
        Then { presentation.description equals "Description" }
    }

    @Test
    fun `map null resource description to empty presentation description`() {
        Given { presentationResource = presentationResource(description = null) }
        When { `map is requested`() }
        Then { presentation.description equals "" }
    }

    @Test
    fun `map non-null resource location to presentation resource`() {
        Given { presentationResource = presentationResource(location = "Location") }
        When { `map is requested`() }
        Then { presentation.location equals "Location" }
    }

    @Test
    fun `map null resource location to empty presentation location`() {
        Given { presentationResource = presentationResource(location = null) }
        When { `map is requested`() }
        Then { presentation.location equals "" }
    }

    @Test
    fun `map non-null resource presenters bio to presentation presenters bio`() {
        Given { presentationResource = presentationResource(presentersBio = "Presenters bio") }
        When { `map is requested`() }
        Then { presentation.presentersBio equals "Presenters bio" }
    }

    @Test
    fun `map null resource presenters bio to empty presenters bio`() {
        Given { presentationResource = presentationResource(presentersBio = null) }
        When { `map is requested`() }
        Then { presentation.presentersBio equals "" }
    }

    @Test
    fun `map resource presenters image to presentation presenters image`() {
        Given { presentationResource = presentationResource(presentersImage = presentersImageResource) }
        When { `map is requested`() }
        Then { presentation.presentersImage equals expectedPresentersImage }
    }

    @Test
    fun `map non-null resource start dateTime to presentation start dateTime`() {
        Given { presentationResource = presentationResource(startTime = startDateTime) }
        When { `map is requested`() }
        Then { presentation.startTime equals startDateTime }
    }

    @Test
    fun `map null resource start dateTime to default dateTime`() {
        Given { presentationResource = presentationResource(startTime = null) }
        When { `map is requested`() }
        Then { presentation.startTime equals Event.DEFAULT_DATE_TIME }
    }

    @Test
    fun `map non-null resource end dateTime to presentation end dateTime`() {
        Given { presentationResource = presentationResource(endTime = endDateTime) }
        When { `map is requested`() }
        Then { presentation.endTime equals endDateTime }
    }

    @Test
    fun `map null resource end dateTime to default dateTime`() {
        Given { presentationResource = presentationResource(endTime = null) }
        When { `map is requested`() }
        Then { presentation.endTime equals Event.DEFAULT_DATE_TIME }
    }

    @Test
    fun `map resource company to presentation company`() {
        Given { presentationResource = presentationResource(company = companyResource) }
        When { `map is requested`() }
        Then { presentation.company equals expectedCompany }
    }

    private fun `map is requested`() {
        presentation = mapper.map(presentationResource)
    }

    private fun `presenters image mapper result`() {
        whenever(imageMapper.map(isNull())).thenReturn(Image())
        whenever(imageMapper.map(presentersImageResource)).thenReturn(expectedPresentersImage)
    }

    private fun `company mapper result`() {
        whenever(companyMapper.map(isNull())).thenReturn(Company())
        whenever(companyMapper.map(companyResource)).thenReturn(expectedCompany)
    }

    private fun presentationResource(
        id: String? = "",
        title: String? = "",
        description: String? = "",
        startTime: DateTime? = DateTime(),
        endTime: DateTime? = DateTime(),
        location: String? = "",
        presentersBio: String? = "",
        presentersImage: PresentationsQuery.Presenter_photo? = null,
        company: PresentationsQuery.Company? = null
    ) = PresentationsQuery.Presentation(
            "",
            id,
            title,
            startTime,
            endTime,
            description,
            location,
            presentersBio,
            presentersImage,
            company
    )
}