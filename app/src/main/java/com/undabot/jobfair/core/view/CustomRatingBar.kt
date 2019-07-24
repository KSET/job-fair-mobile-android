package com.undabot.jobfair.core.view

import android.content.Context
import android.util.AttributeSet
import android.widget.RatingBar

class CustomRatingBar : RatingBar {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle)

    init {
        numStars = 5
        stepSize = 0.01f
        onRatingBarChangeListener = null
    }

    override fun setOnRatingBarChangeListener(listener: OnRatingBarChangeListener?) {
        val decorator = CorrectionDecorator(listener)
        super.setOnRatingBarChangeListener(decorator)
    }

    private class CorrectionDecorator(
        var listener: OnRatingBarChangeListener? = null
    ) : OnRatingBarChangeListener {
        override fun onRatingChanged(ratingBar: RatingBar?, rating: Float, fromUser: Boolean) {
            val correctedRating = if (ratingBar != null) {
                correctToWholeNumber(ratingBar, rating)
            } else {
                rating
            }
            listener?.onRatingChanged(ratingBar, correctedRating, fromUser)
        }

        private fun correctToWholeNumber(ratingBar: RatingBar, rating: Float): Float {
            val correctedRating = Math.ceil(rating.toDouble()).toFloat()
            ratingBar.rating = correctedRating
            return correctedRating
        }
    }
}