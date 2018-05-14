package com.undabot.jobfair.booths.usecases

import com.undabot.jobfair.booths.entities.Booths
import com.undabot.jobfair.booths.repository.BoothsRepository
import com.undabot.jobfair.core.schedulers.MainScheduler
import javax.inject.Inject

class ShowBooths @Inject constructor(
        private val boothsRepository: BoothsRepository,
        private val mainScheduler: MainScheduler) {

    fun with(presenter: ShowsBooths) {
        boothsRepository.booths()
                .map { Booths(it.list) }
                .observeOn(mainScheduler.get())
                .doOnSubscribe { presenter.boothsLoadingInProgress() }
                .subscribe(
                        { presenter.boothsLoaded(it) },
                        { presenter.boothsLoadingErrorWith(it.message.orEmpty()) }
                )
    }

    interface ShowsBooths {
        fun boothsLoadingInProgress()
        fun boothsLoadingErrorWith(errorMessage: String)
        fun boothsLoaded(booths: Booths)
    }
}