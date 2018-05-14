package com.undabot.jobfair.companies.repository.mappers

import IndustriesQuery
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.entities.Industry
import com.undabot.jobfair.equals
import org.junit.Test

class IndustryNetworkMapperShould {

    private val industryNetworkMapper = IndustryNetworkMapper()

    private lateinit var result: Industry
    private lateinit var industry: IndustriesQuery.Industry

    @Test
    fun `map industry id from network to model id`() {
        Given { industryQuery("id") }
        When { `map is requested`() }
        Then { result.id equals "id" }
    }

    @Test
    fun `map industry name from network to model name`() {
        Given { industryQuery(name = "name") }
        When { `map is requested`() }
        Then { result.name equals "name" }
    }

    @Test
    fun `map industry companies from network to model companies`() {
        Given { industryQuery(companyIds = listOf("1")) }
        When { `map is requested`() }
        Then { result.companyIds equals listOf("1") }
    }

    @Test
    fun `map null values to default`() {
        Given { industryQuery() }
        When { `map is requested`() }
        Then { result equals Industry() }
    }

    private fun `map is requested`() {
        result = industryNetworkMapper.map(industry)
    }

    private fun industryQuery(id: String? = null, name: String? = null, companyIds: List<String> = emptyList()) {
        industry = IndustriesQuery.Industry("", id, name, companyIds.map { IndustriesQuery.Company("", it) })
    }
}