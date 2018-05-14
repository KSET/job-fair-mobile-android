package com.undabot.jobfair.booths.view.mappers

import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.booths.entities.Booth
import com.undabot.jobfair.booths.entities.Location
import com.undabot.jobfair.booths.view.models.BoothViewModel
import com.undabot.jobfair.companies.entities.Company
import com.undabot.jobfair.core.entities.Image
import com.undabot.jobfair.equals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BoothViewModelMapperShould {

    private lateinit var mapper: BoothViewModelMapper

    private lateinit var booth: Booth
    private lateinit var viewModel: BoothViewModel
    @Mock private lateinit var location: Location
    private val company: Company = Company(name = "Undabot", image = Image(thumb = "thumb-url"))

    @Before
    fun prepare() {
        mapper = BoothViewModelMapper()
    }

    @Test
    fun `map booth id to view model id`() {
        Given { booth = Booth(id = "10") }
        When { `map is requested`() }
        Then { viewModel.id equals "10" }
    }

    @Test
    fun `map location name to view model snippet`() {
        Given { booth = Booth(locationName = "D2") }
        When { `map is requested`() }
        Then { viewModel.snippet equals "D2" }
    }

    @Test
    fun `map company name to view model title`() {
        Given { booth = Booth(company = company) }
        When { `map is requested`() }
        Then { viewModel.title equals "Undabot" }
    }

    @Test
    fun `map booth location to view model location`() {
        Given { booth = Booth(location = location) }
        When { `map is requested`() }
        Then { viewModel.location equals location }
    }

    @Test
    fun `map company thumbnail to view model image`() {
        Given { booth = Booth(company = company) }
        When { `map is requested`() }
        Then { viewModel.imageUrl equals "thumb-url" }
    }

    @Test
    fun `map booth company to view model company`() {
        Given { booth = Booth(company = company) }
        When { `map is requested`() }
        Then { viewModel.company equals company }
    }

    private fun `map is requested`() {
        viewModel = mapper.map(booth)
    }
}