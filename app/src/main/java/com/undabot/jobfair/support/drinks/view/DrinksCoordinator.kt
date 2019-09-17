package com.undabot.jobfair.support.drinks.view

import com.undabot.jobfair.core.view.AbsCoordinator
import com.undabot.jobfair.support.drinks.usecases.RequestDrinks
import com.undabot.jobfair.support.drinks.usecases.ShowAvailableDrinks
import com.undabot.jobfair.support.drinks.view.DrinksContract.Coordinator
import com.undabot.jobfair.support.drinks.view.DrinksContract.Presenter
import com.undabot.jobfair.support.drinks.view.DrinksContract.View
import javax.inject.Inject

class DrinksCoordinator @Inject constructor(
    presenter: Presenter,
    private val showAvailableDrinks: ShowAvailableDrinks,
    private val requestDrinks: RequestDrinks
) : AbsCoordinator<View, Presenter>(presenter), Coordinator {

    override fun availableDrinksRequested() {
        showAvailableDrinks(presenter)
    }

    override fun drinksRequested(drinks: List<DrinkViewModel>) {
        requestDrinks(drinks.associate { it.name to it.count }, presenter)
    }
}