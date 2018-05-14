package com.undabot.jobfair.companies.repository.mappers

import CompanyDetailsQuery
import com.nhaarman.mockito_kotlin.isNull
import com.nhaarman.mockito_kotlin.whenever
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.booths.entities.Location
import com.undabot.jobfair.booths.repository.mappers.BoothLocationMapper
import com.undabot.jobfair.companies.entities.DetailedCompany
import com.undabot.jobfair.companies.entities.DetailedCompany.Companion.DEFAULT_DATE_TIME
import com.undabot.jobfair.equals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CompanyDetailsNetworkMapperShould {

    private val now = DEFAULT_DATE_TIME
    private lateinit var mapper: CompanyDetailsNetworkMapper

    private lateinit var result: DetailedCompany
    private lateinit var queryResult: CompanyDetailsQuery.Company
    private val geolocation: String = "geolocation"
    @Mock private lateinit var boothLocationMapper: BoothLocationMapper
    @Mock private lateinit var expectedLocation: Location

    @Before
    fun prepare() {
        mapper = CompanyDetailsNetworkMapper(boothLocationMapper)
        whenever(boothLocationMapper.map(isNull())).thenReturn(Location())
        whenever(boothLocationMapper.map(geolocation)).thenReturn(expectedLocation)
    }

    @Test
    fun `map network id to model`() {
        Given { companyWith(id = "1") }
        When { `map is requested`() }
        Then { result.id equals "1" }
    }

    @Test
    fun `map network name to model`() {
        Given { companyWith(name = "name") }
        When { `map is requested`() }
        Then { result.name equals "name" }
    }

    @Test
    fun `map network logo to model`() {
        Given {
            companyWith(logo = CompanyDetailsQuery.Logo("",
                    CompanyDetailsQuery.Medium("", "url")))
        }
        When { `map is requested`() }
        Then { result.logoUrl equals "url" }
    }

    @Test
    fun `map network industry to model`() {
        Given { companyWith(industry = CompanyDetailsQuery.Industry("", "industry")) }
        When { `map is requested`() }
        Then { result.industryName equals "industry" }
    }

    @Test
    fun `map network workshop to model`() {
        Given {
            companyWith(workshop = CompanyDetailsQuery.Workshop("", "title", now))
        }
        When { `map is requested`() }
        Then {
            result.workshopName equals "title"
            result.workshopDate equals now
        }
    }

    @Test
    fun `map network presentation to model`() {
        Given {
            companyWith(presentation =
            CompanyDetailsQuery.Presentation("", "title", now))
        }
        When { `map is requested`() }
        Then {
            result.presentationName equals "title"
            result.presentationDate equals now
        }
    }

    @Test
    fun `map network description to model`() {
        Given { companyWith(desc = "desc") }
        When { `map is requested`() }
        Then { result.description equals "desc" }
    }

    @Test
    fun `map network cocktail to model`() {
        Given { companyWith(cocktail = CompanyDetailsQuery.Cocktail("", "cocktail")) }
        When { `map is requested`() }
        Then { result.cocktail equals "cocktail" }
    }

    @Test
    fun `map network booth location to model`() {
        Given {
            companyWith(booth = CompanyDetailsQuery.Booth("", "location", geolocation))
        }
        When { `map is requested`() }
        Then {
            result.boothLocation equals "location"
            result.boothGeolocation equals expectedLocation
        }
    }

    @Test
    fun `map null values to default values in model`() {
        Given { companyWith() }
        When { `map is requested`() }
        Then { result equals DetailedCompany(workshopDate = now, presentationDate = now) }
    }

    private fun `map is requested`() {
        result = mapper.map(queryResult)
    }

    private fun companyWith(
            id: String? = null,
            name: String? = null,
            url: String? = null,
            logo: CompanyDetailsQuery.Logo? = null,
            industry: CompanyDetailsQuery.Industry? = null,
            workshop: CompanyDetailsQuery.Workshop? = null,
            presentation: CompanyDetailsQuery.Presentation? = null,
            desc: String? = null,
            cocktail: CompanyDetailsQuery.Cocktail? = null,
            booth: CompanyDetailsQuery.Booth? = null
    ) {
        queryResult = CompanyDetailsQuery.Company("", id, name, url, logo, industry,
                workshop, presentation, desc, cocktail, booth)
    }
}