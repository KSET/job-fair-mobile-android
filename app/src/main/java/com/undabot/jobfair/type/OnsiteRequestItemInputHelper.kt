package com.undabot.jobfair.type

import com.undabot.jobfair.R

fun String.toAssistanceItem() = listOf(
    OnsiteRequestItemInput.Builder()
        .request_type(OnsiteRequestType.ASSISTANCE)
        .note(this)
        .build()
)

fun Map<Int, Int>.toDrinkItems(): List<OnsiteRequestItemInput> =
    entries
        .map { it.toDrinkItem() }
        .toList()

private fun Map.Entry<Int, Int>.toDrinkItem(): OnsiteRequestItemInput =
    OnsiteRequestItemInput.Builder()
        .request_type(this.key.toRequstType())
        .amount(this.value)
        .build()

private fun Int.toRequstType(): OnsiteRequestType =
    when (this) {
        R.string.drinks_macchiato -> OnsiteRequestType.MACCHIATO
        R.string.drinks_espresso -> OnsiteRequestType.ESPRESSO
        R.string.drinks_water -> OnsiteRequestType.WATER
        else -> throw RuntimeException("Drink not recognized")
    }