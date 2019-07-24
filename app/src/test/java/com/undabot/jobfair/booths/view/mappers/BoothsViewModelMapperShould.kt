package com.undabot.jobfair.booths.view.mappers

import com.nhaarman.mockitokotlin2.whenever
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.booths.entities.Booth
import com.undabot.jobfair.booths.entities.Booths
import com.undabot.jobfair.booths.view.models.BoothViewModel
import com.undabot.jobfair.booths.view.models.BoothsViewModel
import com.undabot.jobfair.equals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class BoothsViewModelMapperShould {

    private lateinit var mapper: BoothsViewModelMapper

    @Mock private lateinit var booth1: Booth
    @Mock private lateinit var booth2: Booth
    @Mock private lateinit var boothViewModel1: BoothViewModel
    @Mock private lateinit var boothViewModel2: BoothViewModel
    @Mock private lateinit var boothViewModelMapper: BoothViewModelMapper
    private lateinit var booths: Booths
    private lateinit var boothsViewModel: BoothsViewModel

    @Before
    fun prepare() {
        mapper = BoothsViewModelMapper(boothViewModelMapper)
        whenever(boothViewModelMapper.map(booth1)).thenReturn(boothViewModel1)
        whenever(boothViewModelMapper.map(booth2)).thenReturn(boothViewModel2)
    }

    @Test
    fun `map booths list to view model list`() {
        Given { booths = Booths(arrayListOf(booth2, booth1)) }
        When { `map is requested`() }
        Then { boothsViewModel equals BoothsViewModel(arrayListOf(boothViewModel2, boothViewModel1)) }
    }

    private fun `map is requested`() {
        boothsViewModel = mapper.map(booths)
    }
}