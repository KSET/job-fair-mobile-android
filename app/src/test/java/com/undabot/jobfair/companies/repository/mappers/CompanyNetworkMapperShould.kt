package com.undabot.jobfair.companies.repository.mappers

import com.undabot.jobfair.CompaniesQuery
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.core.entities.Image
import com.undabot.jobfair.equals
import org.junit.Test

class CompanyNetworkMapperShould {

    private val mapper = CompanyNetworkMapper()
    private lateinit var result: Company
    private lateinit var company: CompaniesQuery.Company

    @Test
    fun `map id from network to model`() {
        Given { companyWith(id = "1") }
        When { `map is requested`() }
        Then { result.id equals "1" }
    }

    @Test
    fun `map name from network to model`() {
        Given { companyWith(name = "name") }
        When { `map is requested`() }
        Then { result.name equals "name" }
    }

    @Test
    fun `map logo url from network to model`() {
        Given { companyWith(logo = "url") }
        When { `map is requested`() }
        Then { result.image equals Image(small = "url") }
    }

    @Test
    fun `map null values to default`() {
        Given { companyWith() }
        When { `map is requested`() }
        Then { result equals Company() }
    }

    private fun `map is requested`() {
        result = mapper.map(company)
    }

    private fun companyWith(id: String? = null, name: String? = null, logo: String? = null) {
        company = CompaniesQuery.Company("", id, name,
            CompaniesQuery.Logo("", CompaniesQuery.Small("", logo)))
    }
}