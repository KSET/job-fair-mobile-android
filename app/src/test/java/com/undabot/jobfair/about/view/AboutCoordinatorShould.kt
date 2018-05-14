package com.undabot.jobfair.about.view

import com.nhaarman.mockito_kotlin.verify
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import com.undabot.jobfair.about.Links
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AboutCoordinatorShould {

    private lateinit var coordinator: AboutContract.Coordinator

    @Mock private lateinit var presenter: AboutContract.Presenter
    @Mock private lateinit var view: AboutContract.View

    @Before
    fun prepare() {
        coordinator = AboutCoordinator(presenter)
        coordinator.bind(view)
    }

    @Test
    fun `open map url when map is pressed`() {
        When { coordinator.onMapPressed() }
        Then { verify(presenter).openUrl(Links.MAP) }
    }

    @Test
    fun `open web url when web is pressed`() {
        When { coordinator.onWebPressed() }
        Then { verify(presenter).openUrl(Links.WEB) }
    }

    @Test
    fun `open email url when email is pressed`() {
        When { coordinator.onEmailPressed() }
        Then { verify(presenter).openUrl(Links.EMAIL) }
    }

    @Test
    fun `open facebook url when facebook is pressed`() {
        When { coordinator.onFacebookPressed() }
        Then { verify(presenter).openUrl(Links.FACEBOOK) }
    }

    @Test
    fun `open instagram url when instagram is pressed`() {
        When { coordinator.onInstagramPressed() }
        Then { verify(presenter).openUrl(Links.INSTAGRAM) }
    }

    @Test
    fun `open youtube link when youtube is pressed`() {
        When { coordinator.onYouTubeChannelPressed() }
        Then { verify(presenter).openUrl(Links.YOUTUBE) }
    }

    @Test
    fun `open day one stream url when day one is pressed`() {
        When { coordinator.onDayOneStreamPressed() }
        Then { verify(presenter).openUrl(Links.DAY_ONE_STREAM) }
    }

    @Test
    fun `open day two stream url when day two is pressed`() {
        When { coordinator.onDayTwoStreamPressed() }
        Then { verify(presenter).openUrl(Links.DAY_TWO_STREAM) }
    }

    @Test
    fun `share job fair url when share is pressed`() {
        When { coordinator.onSharePressed() }
        Then { verify(presenter).shareUrl(Links.WEB) }
    }

    @Test
    fun `open developer web when developer info is pressed`() {
        When { coordinator.onDeveloperInfoPressed() }
        Then { verify(presenter).openUrl(Links.DEVELOPER_WEB) }
    }
}