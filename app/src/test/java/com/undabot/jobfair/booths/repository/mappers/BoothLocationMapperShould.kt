package com.undabot.jobfair.booths.repository.mappers

import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.booths.entities.Location
import com.undabot.jobfair.equals
import org.junit.Before
import org.junit.Test

class BoothLocationMapperShould {

    private lateinit var mapper: BoothLocationMapper

    private var geolocationResource: String? = null
    private lateinit var location: Location

    @Before
    fun prepare() {
        mapper = BoothLocationMapper()
    }

    @Test
    fun `map null geolocation resource to default location`() {
        Given { geolocationResource = null }
        When { `map is requested`() }
        Then { location equals Location() }
    }

    @Test
    fun `map empty geolocation parameter as default location`() {
        Given { geolocationResource = "" }
        When { `map is requested`() }
        Then { location equals Location() }
    }

    @Test
    fun `map first geolocation parameter as location latitude`() {
        Given { geolocationResource = "12.4,13.1818,21" }
        When { `map is requested`() }
        Then { location.latitude equals 12.4 }
    }

    @Test
    fun `map second geolocation parameter as location longitude`() {
        Given { geolocationResource = "12.4,13.1818,21" }
        When { `map is requested`() }
        Then { location.longitude equals 13.1818 }
    }

    private fun `map is requested`() {
        location = mapper.map(geolocationResource)
    }
}