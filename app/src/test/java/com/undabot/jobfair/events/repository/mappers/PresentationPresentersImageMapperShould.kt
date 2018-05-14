package com.undabot.jobfair.events.repository.mappers

import PresentationsQuery
import com.undabot.jobfair.Given
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.core.entities.Image
import com.undabot.jobfair.equals
import org.junit.Before
import org.junit.Test

class PresentationPresentersImageMapperShould {

    private lateinit var mapper: PresentationPresentersImageMapper

    private lateinit var image: Image
    private var imageResource: PresentationsQuery.Presenter_photo? = null

    @Before
    fun prepare() {
        mapper = PresentationPresentersImageMapper()
    }

    @Test
    fun `map null image resource to default image`() {
        Given { imageResource = null }
        When { `map is requested`() }
        Then { image equals Image() }
    }

    @Test
    fun `map non null image resource with null large url to default image`() {
        Given { imageResource = imageResource(thumb = null) }
        When { `map is requested`() }
        Then { image equals Image() }
    }

    @Test
    fun `map non null image resource with non null large url to image with large url`() {
        Given { imageResource = imageResource(thumb = PresentationsQuery.Thumb("", "thumb-url")) }
        When { `map is requested`() }
        Then { image equals Image(thumb = "thumb-url") }
    }

    private fun imageResource(thumb: PresentationsQuery.Thumb?) =
            PresentationsQuery.Presenter_photo("", thumb)

    private fun `map is requested`() {
        image = mapper.map(imageResource)
    }
}