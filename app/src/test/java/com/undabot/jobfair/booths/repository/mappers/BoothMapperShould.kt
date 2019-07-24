package com.undabot.jobfair.booths.repository.mappers

import com.nhaarman.mockitokotlin2.isNull
import com.nhaarman.mockitokotlin2.whenever
import com.undabot.jobfair.BoothsQuery
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.booths.entities.Booth
import com.undabot.jobfair.booths.entities.Location
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.equals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BoothMapperShould {

    private lateinit var mapper: BoothMapper

    private lateinit var boothResource: BoothsQuery.Booth
    private lateinit var booth: Booth
    private val geolocation: String = "12.23,15.23"
    @Mock private lateinit var locationMapper: BoothLocationMapper
    @Mock private lateinit var companyMapper: BoothCompanyMapper
    @Mock private lateinit var expectedLocation: Location
    @Mock private lateinit var companyResource: BoothsQuery.Company
    @Mock private lateinit var expectedCompany: Company

    @Before
    fun prepare() {
        mapper = BoothMapper(companyMapper, locationMapper)
        `location mapper result`()
        `company mapper result`()
    }

    private fun `company mapper result`() {
        whenever(companyMapper.map(isNull())).thenReturn(Company())
        whenever(companyMapper.map(companyResource)).thenReturn(expectedCompany)
    }

    private fun `location mapper result`() {
        whenever(locationMapper.map(isNull())).thenReturn(Location())
        whenever(locationMapper.map(geolocation)).thenReturn(expectedLocation)
    }

    @Test
    fun `map non-null resource id to booth id`() {
        Given { boothResource = `booth resource`(id = "1") }
        When { `map is requested`() }
        Then { booth.id equals "1" }
    }

    @Test
    fun `map null resource id to empty booth id`() {
        Given { boothResource = `booth resource`(id = null) }
        When { `map is requested`() }
        Then { booth.id equals "" }
    }

    @Test
    fun `map non-null resource location to booth location name`() {
        Given { boothResource = `booth resource`(location = "D2") }
        When { `map is requested`() }
        Then { booth.locationName equals "D2" }
    }

    @Test
    fun `map null resource location to empty booth location name`() {
        Given { boothResource = `booth resource`(location = null) }
        Then { `map is requested`() }
        Then { booth.locationName equals "" }
    }

    @Test
    fun `map resource geolocation to booth location`() {
        Given { boothResource = `booth resource`(geolocation = geolocation) }
        When { `map is requested`() }
        Then { booth.location equals expectedLocation }
    }

    @Test
    fun `map resource company to booth company`() {
        Given { boothResource = `booth resource`(company = companyResource) }
        When { `map is requested`() }
        Then { booth.company equals expectedCompany }
    }

    private fun `map is requested`() {
        booth = mapper.map(boothResource)
    }

    private fun `booth resource`(
            id: String? = "",
            location: String? = "",
            geolocation: String? = null,
            company: BoothsQuery.Company? = null
    ) = BoothsQuery.Booth(
            "",
            id,
            location,
            geolocation,
            "",
            "",
            company
    )
}