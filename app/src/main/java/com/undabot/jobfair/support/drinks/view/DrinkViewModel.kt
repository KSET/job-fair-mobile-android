package com.undabot.jobfair.support.drinks.view

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class DrinkViewModel(
    @StringRes val name: Int,
    @DrawableRes val image: Int,
    var count: Int = 0
)