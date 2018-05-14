package com.undabot.jobfair.about.view

import com.nhaarman.mockito_kotlin.verify
import com.undabot.jobfair.Then
import com.undabot.jobfair.When
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class AboutPresenterShould {

    private lateinit var presenter: AboutContract.Presenter

    @Mock private lateinit var view: AboutContract.View

    @Before
    fun prepare() {
        presenter = AboutPresenter()
        presenter.bind(view)
    }

    @Test
    fun `open url when requested`() {
        When { presenter.openUrl("url") }
        Then { verify(view).openUrl("url") }
    }

    @Test
    fun `share url when requested`() {
        When { presenter.shareUrl("url") }
        Then { verify(view).shareUrl("url") }
    }
}