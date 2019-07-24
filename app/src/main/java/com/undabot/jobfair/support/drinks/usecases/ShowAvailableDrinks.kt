package com.undabot.jobfair.support.drinks.usecases

import com.undabot.jobfair.support.drinks.models.Drink
import com.undabot.jobfair.support.repository.SupportRepository
import javax.inject.Inject

class ShowAvailableDrinks @Inject constructor(
    private val repository: SupportRepository
) {

    operator fun invoke(presenter: ShowsAvailableDrinks) {
        presenter.showDrinks(repository.availableDrinks())
    }

    interface ShowsAvailableDrinks {
        fun showDrinks(drinks: List<Drink>)
    }
}