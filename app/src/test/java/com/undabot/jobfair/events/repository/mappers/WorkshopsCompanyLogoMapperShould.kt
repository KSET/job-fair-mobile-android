package com.undabot.jobfair.events.repository.mappers

import WorkshopsQuery
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.core.entities.Image
import com.undabot.jobfair.equals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class WorkshopsCompanyLogoMapperShould {

    private lateinit var mapper: WorkshopsCompanyLogoMapper

    private lateinit var image: Image
    private var logoResource: WorkshopsQuery.Logo? = null

    @Before
    fun prepare() {
        mapper = WorkshopsCompanyLogoMapper()
    }

    @Test
    fun `map null logo resource to default image`() {
        Given { logoResource = null }
        When { `map is requested`() }
        Then { image equals Image() }
    }

    @Test
    fun `map non null logo resource with null large url to default image`() {
        Given { logoResource = logoResource(large = null) }
        When { `map is requested`() }
        Then { image equals Image() }
    }

    @Test
    fun `map non null logo resource with non null large url to image with large url`() {
        Given { logoResource = logoResource(large = WorkshopsQuery.Large("", "large-url")) }
        When { `map is requested`() }
        Then { image equals Image(large = "large-url") }
    }

    private fun logoResource(large: WorkshopsQuery.Large?) =
            WorkshopsQuery.Logo("", large)

    private fun `map is requested`() {
        image = mapper.map(logoResource)
    }
}