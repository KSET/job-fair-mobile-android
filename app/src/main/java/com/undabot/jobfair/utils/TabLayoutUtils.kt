package com.undabot.jobfair.utils

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout

fun TabLayout.setupWithViewPager(
    viewPager: ViewPager,
    marginStart: Int = 0,
    marginBetween: Int = 0,
    marginEnd: Int = 0
) {
    setupWithViewPager(viewPager)

    val tabs = getChildAt(0) as ViewGroup
    val startInPx = marginStart.dpToPx(context)
    val betweenInPx = marginBetween.dpToPx(context)
    val endInPx = marginEnd.dpToPx(context)

    val tab1 = tabs.getChildAt(0)
    tab1.setMargins(start = startInPx, end = betweenInPx / 2)

    val tab2 = tabs.getChildAt(1)
    tab2.setMargins(start = betweenInPx / 2, end = endInPx)

    requestLayout()
}

private fun View.setMargins(start: Int, end: Int) {
    layoutParams = (layoutParams as LinearLayout.LayoutParams).apply {
        marginStart = start
        marginEnd = end
    }
}