package com.undabot.jobfair.support.repository

import com.undabot.jobfair.R
import com.undabot.jobfair.core.di.scope.PerApplication
import com.undabot.jobfair.core.schedulers.WorkerScheduler
import com.undabot.jobfair.networking.services.ResourceApiService
import com.undabot.jobfair.support.drinks.models.Drink
import com.undabot.jobfair.support.entities.SupportResult
import com.undabot.jobfair.support.repository.mappers.SupportMapper
import com.undabot.jobfair.type.toAssistanceItem
import com.undabot.jobfair.type.toDrinkItems
import io.reactivex.Single
import javax.inject.Inject

@PerApplication
class SupportRepositoryImpl @Inject constructor(
    private val resourceApiService: ResourceApiService,
    private val workerScheduler: WorkerScheduler,
    private val supportMapper: SupportMapper
) : SupportRepository {

    private val drinks = listOf(
        Drink(R.string.drinks_macchiato, R.drawable.ic_macchiato),
        Drink(R.string.drinks_espresso, R.drawable.ic_espresso),
        Drink(R.string.drinks_water, R.drawable.ic_water)
    )

    override fun availableDrinks(): List<Drink> = drinks

    override fun requestAssistance(companyId: String, note: String): Single<SupportResult> =
        resourceApiService.onSiteRequest(companyId, note.toAssistanceItem())
            .map { supportMapper.map(it.data()) }
            .subscribeOn(workerScheduler.get())

    override fun requestDrinks(companyId: String, drinks: Map<Int, Int>): Single<SupportResult> =
        resourceApiService.onSiteRequest(companyId, drinks.toDrinkItems())
            .map { supportMapper.map(it.data()) }
            .subscribeOn(workerScheduler.get())
}
