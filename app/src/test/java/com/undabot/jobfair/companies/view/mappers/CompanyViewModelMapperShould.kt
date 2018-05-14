package com.undabot.jobfair.companies.view.mappers

import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.companies.view.models.CompanyViewModel
import com.undabot.jobfair.core.entities.Image
import com.undabot.jobfair.equals
import org.junit.Before
import org.junit.Test

class CompanyViewModelMapperShould {

    private lateinit var mapper: CompanyViewModelMapper

    private lateinit var company: Company
    private lateinit var viewModel: CompanyViewModel

    @Before
    fun prepare() {
        mapper = CompanyViewModelMapper()
    }

    @Test
    fun `map model id to view model id`() {
        Given { company = Company(id = "1") }
        When { `map is requested`() }
        Then { viewModel.id equals "1" }
    }

    @Test
    fun `map model name to view model name`() {
        Given { company = Company(name = "name") }
        When { `map is requested`() }
        Then { viewModel.name equals "name" }
    }

    @Test
    fun `map model small image to view model logo url`() {
        Given { company = Company(image = Image(small = "small-url")) }
        When { `map is requested`() }
        Then { viewModel.logoUrl equals "small-url" }
    }

    private fun `map is requested`() {
        viewModel = mapper.map(company)
    }
}