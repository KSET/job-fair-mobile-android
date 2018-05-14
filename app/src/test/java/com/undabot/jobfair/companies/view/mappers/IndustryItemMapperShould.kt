package com.undabot.jobfair.companies.view.mappers

import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.companies.entities.Industry
import com.undabot.jobfair.companies.view.models.IndustryViewModel
import com.undabot.jobfair.equals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class IndustryItemMapperShould {

    private lateinit var mapper: IndustryItemMapper

    private lateinit var industry: Industry
    private lateinit var viewModel: IndustryViewModel
    @Mock private lateinit var companyIds: List<String>

    @Before
    fun prepare() {
        mapper = IndustryItemMapper()
    }

    @Test
    fun `map model id to view model id`() {
        Given { industry = Industry(id = "1") }
        When { `map is requested`() }
        Then { viewModel.id equals "1" }
    }

    @Test
    fun `map model name to view model name`() {
        Given { industry = Industry(name = "Name") }
        When { `map is requested`() }
        Then { viewModel.name equals "Name" }
    }

    @Test
    fun `map model company ids list to view model list`() {
        Given { industry = Industry(companyIds = companyIds) }
        When { `map is requested`() }
        Then { viewModel.companies equals companyIds }
    }

    private fun `map is requested`() {
        viewModel = mapper.map(industry)
    }
}