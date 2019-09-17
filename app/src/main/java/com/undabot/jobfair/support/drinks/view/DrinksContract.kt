package com.undabot.jobfair.support.drinks.view

import com.undabot.jobfair.core.view.BaseCoordinator
import com.undabot.jobfair.core.view.BasePresenter
import com.undabot.jobfair.support.drinks.usecases.RequestDrinks
import com.undabot.jobfair.support.drinks.usecases.ShowAvailableDrinks

interface DrinksContract {

    interface View {
        fun showDrinksRequestSuccess(message: CharSequence)
        fun showDrinks(viewModels: List<DrinkViewModel>)
        fun showError()
    }

    interface Coordinator : BaseCoordinator<View> {
        fun availableDrinksRequested()
        fun drinksRequested(drinks: List<DrinkViewModel>)
    }

    interface Presenter : BasePresenter<View>,
        ShowAvailableDrinks.ShowsAvailableDrinks,
        RequestDrinks.RequestsDrinks
}