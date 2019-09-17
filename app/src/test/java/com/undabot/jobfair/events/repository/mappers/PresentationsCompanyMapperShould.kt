package com.undabot.jobfair.events.repository.mappers

import com.nhaarman.mockitokotlin2.isNull
import com.nhaarman.mockitokotlin2.whenever
import com.undabot.jobfair.Given
import com.undabot.jobfair.PresentationsQuery
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.core.entities.Image
import com.undabot.jobfair.equals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class PresentationsCompanyMapperShould {

    private lateinit var mapper: PresentationsCompanyMapper

    private var companyResource: PresentationsQuery.Company? = null

    private lateinit var company: Company
    @Mock private lateinit var companyLogoMapper: PresentationsCompanyLogoMapper
    @Mock private lateinit var expectedCompanyLogo: Image
    @Mock private lateinit var companyLogoResource: PresentationsQuery.Logo

    @Before
    fun prepare() {
        mapper = PresentationsCompanyMapper(companyLogoMapper)
        `company logo mapper result`()
    }

    @Test
    fun `map non-null resource id to company id`() {
        Given { companyResource = companyResource(id = "1") }
        When { `map is requested`() }
        Then { company.id equals "1" }
    }

    @Test
    fun `map null resource id to empty company id`() {
        Given { companyResource = companyResource(id = null) }
        When { `map is requested`() }
        Then { company.id equals "" }
    }

    @Test
    fun `map non-null resource name to company name`() {
        Given { companyResource = companyResource(name = "Name") }
        When { `map is requested`() }
        Then { company.name equals "Name" }
    }

    @Test
    fun `map null resource name to empty company name`() {
        Given { companyResource = companyResource(name = null) }
        When { `map is requested`() }
        Then { company.name equals "" }
    }

    @Test
    fun `map company logo resource to company image`() {
        Given { companyResource = companyResource(logo = companyLogoResource) }
        When { `map is requested`() }
        Then { company.image equals expectedCompanyLogo }
    }

    @Test
    fun `map null company resource to default company`() {
        Given { companyResource = null }
        When { `map is requested`() }
        Then { company equals Company() }
    }

    private fun `map is requested`() {
        company = mapper.map(companyResource)
    }

    private fun `company logo mapper result`() {
        whenever(companyLogoMapper.map(isNull())).thenReturn(Image())
        whenever(companyLogoMapper.map(companyLogoResource)).thenReturn(expectedCompanyLogo)
    }

    private fun companyResource(
        id: String? = "",
        name: String? = "",
        logo: PresentationsQuery.Logo? = null
    ) = PresentationsQuery.Company(
        "", id, name, logo
    )
}