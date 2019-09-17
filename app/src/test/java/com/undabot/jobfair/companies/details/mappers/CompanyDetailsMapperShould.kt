package com.undabot.jobfair.companies.details.mappers

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.booths.entities.Location
import com.undabot.jobfair.booths.entities.LocationInfo
import com.undabot.jobfair.companies.details.view.models.CompanyDetailsViewModel
import com.undabot.jobfair.companies.entities.DetailedCompany
import com.undabot.jobfair.equals
import com.undabot.jobfair.utils.DateTimeFormatter
import org.joda.time.DateTime
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class CompanyDetailsMapperShould {

    private lateinit var mapper: CompanyDetailsMapper

    private val formattedStartDateTime = "May 14, 09:30 AM"
    private val now = DateTime.now()
    private lateinit var result: CompanyDetailsViewModel
    private lateinit var company: DetailedCompany
    @Mock private lateinit var datetimeFormatter: DateTimeFormatter
    @Mock private lateinit var boothLocation: Location

    @Before
    fun prepare() {
        whenever(datetimeFormatter.formatToDateAndTime(any())).thenReturn(formattedStartDateTime)
        mapper = CompanyDetailsMapper(datetimeFormatter)
    }

    @Test
    fun `map company details id to viewmodel`() {
        Given { companyWith(id = "1") }
        When { `map is requested`() }
        Then { result.id equals "1" }
    }

    @Test
    fun `map company details name to viewmodel`() {
        Given { companyWith(name = "Company") }
        When { `map is requested`() }
        Then { result.name equals "Company" }
    }

    @Test
    fun `map company details logo url to viewmodel`() {
        Given { companyWith(logoUrl = "logo") }
        When { `map is requested`() }
        Then { result.logoUrl equals "logo" }
    }

    @Test
    fun `map company details web url to viewmodel`() {
        Given { companyWith(websiteUrl = "web") }
        When { `map is requested`() }
        Then { result.websiteUrl equals "web" }
    }

    @Test
    fun `map company details industry name to viewmodel`() {
        Given { companyWith(industryName = "industry") }
        When { `map is requested`() }
        Then { result.industry equals "industry" }
    }

    @Test
    fun `map company details workshop to viewmodel`() {
        Given { companyWith(workshopName = "name") }
        When { `map is requested`() }
        Then {
            result.workshopData equals "name • $formattedStartDateTime"
        }
    }

    @Test
    fun `map company details presentation name to viewmodel`() {
        Given { companyWith(presentationName = "industry") }
        When { `map is requested`() }
        Then { result.presentationData equals "industry • $formattedStartDateTime" }
    }

    @Test
    fun `map company details description to viewmodel`() {
        Given { companyWith(description = "desc") }
        When { `map is requested`() }
        Then { result.description equals "desc" }
    }

    @Test
    fun `map company details cocktail to viewmodel`() {
        Given { companyWith(cocktail = "cocktail") }
        When { `map is requested`() }
        Then { result.cocktailName equals "cocktail" }
    }

    @Test
    fun `map company details booth to viewmodel`() {
        Given { companyWith(boothLocation = "booth", boothGeolocation = boothLocation) }
        When { `map is requested`() }
        Then { result.booth equals LocationInfo("booth", boothLocation) }
    }

    private fun `map is requested`() {
        result = mapper.map(company)
    }

    private fun companyWith(
            id: String = "",
            name: String = "",
            logoUrl: String = "",
            websiteUrl: String = "",
            industryName: String = "",
            workshopName: String = "",
            workshopDate: DateTime = now,
            presentationName: String = "",
            presentationDate: DateTime = now,
            description: String = "",
            cocktail: String = "",
            boothLocation: String = "",
            boothGeolocation: Location = Location()
    ) {
        company = DetailedCompany(id, name, logoUrl, websiteUrl, industryName, workshopName,
                workshopDate, presentationName, presentationDate,
                description, cocktail, boothLocation, boothGeolocation)
    }
}