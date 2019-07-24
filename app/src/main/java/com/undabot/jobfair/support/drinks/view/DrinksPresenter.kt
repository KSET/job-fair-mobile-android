package com.undabot.jobfair.support.drinks.view

import android.content.Context
import android.graphics.Typeface
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.StyleSpan
import androidx.core.content.res.ResourcesCompat
import com.undabot.jobfair.R
import com.undabot.jobfair.core.view.AbsPresenter
import com.undabot.jobfair.support.drinks.models.Drink
import javax.inject.Inject

class DrinksPresenter @Inject constructor(
    private val context: Context
) : AbsPresenter<DrinksContract.View>(), DrinksContract.Presenter {

    override fun showDrinks(drinks: List<Drink>) = onView {
        it.showDrinks(
            drinks.map { drink ->
                DrinkViewModel(
                    name = drink.name,
                    image = drink.image,
                    count = 0
                )
            }
        )
    }

    override fun drinksRequested(drinks: Map<Int, Int>) = onView {
        val message = drinks.toSpannableString()
        it.showDrinksRequestSuccess(message)
    }

    override fun drinksRequestFailed() = onView {
        it.showError()
    }

    private fun Map<Int, Int>.toSpannableString(): SpannableString =
        entries.joinTo(SpannableStringBuilder(), separator = "\n") {
            val number = SpannableString("${it.value}")
            val typeface: Typeface = ResourcesCompat.getFont(context, R.font.montserrat_extra_bold)!!
            number.setSpan(StyleSpan(typeface.style), 0, number.length, 0)
            val drink = context.getString(it.key)

            TextUtils.concat(number, " ", drink)
        }
            .let { SpannableString(it) }
}