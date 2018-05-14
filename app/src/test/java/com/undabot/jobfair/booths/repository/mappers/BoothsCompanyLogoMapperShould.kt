package com.undabot.jobfair.booths.repository.mappers

import BoothsQuery
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
class BoothsCompanyLogoMapperShould {

    private lateinit var mapper: BoothCompanyLogoMapper

    private lateinit var image: Image
    private var logoResource: BoothsQuery.Logo? = null

    @Before
    fun prepare() {
        mapper = BoothCompanyLogoMapper()
    }

    @Test
    fun `map null logo resource to default image`() {
        Given { logoResource = null }
        When { `map is requested`() }
        Then { image equals Image() }
    }

    @Test
    fun `map non null logo resource with null thumb url to default image`() {
        Given { logoResource = logoResource(large = null) }
        When { `map is requested`() }
        Then { image equals Image() }
    }

    @Test
    fun `map non null logo resource with non null thumb url to image with thumb url`() {
        Given { logoResource = logoResource(large = BoothsQuery.Thumb("", "thumb-url")) }
        When { `map is requested`() }
        Then { image equals Image(thumb = "thumb-url") }
    }

    private fun logoResource(large: BoothsQuery.Thumb?) =
            BoothsQuery.Logo("", large)

    private fun `map is requested`() {
        image = mapper.map(logoResource)
    }
}