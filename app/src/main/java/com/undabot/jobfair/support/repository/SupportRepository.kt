package com.undabot.jobfair.support.repository

import com.undabot.jobfair.support.drinks.models.Drink
import com.undabot.jobfair.support.entities.SupportResult
import io.reactivex.Single

interface SupportRepository {

    fun requestAssistance(companyId: String, note: String): Single<SupportResult>

    fun requestDrinks(companyId: String, drinks: Map<Int, Int>): Single<SupportResult>

    fun availableDrinks(): List<Drink>
}