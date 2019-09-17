package com.undabot.jobfair.support.drinks.models

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Drink(
    @StringRes val name: Int,
    @DrawableRes val image: Int
)