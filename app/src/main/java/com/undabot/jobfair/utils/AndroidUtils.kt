package com.undabot.jobfair.utils

import android.content.Context
import android.content.Intent
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.TextView

fun Context.canHandle(intent: Intent): Boolean =
        this.packageManager.queryIntentActivities(intent, 0).isNotEmpty()

fun TextView.setTextOrHideIfEmpty(text: String?, vararg otherViews: View) {
    if (text.isNullOrEmpty()) {
        this.visibility = View.GONE
        otherViews.forEach { it.visibility = View.GONE }
    } else {
        this.text = text
    }
}

fun ViewPager.isFirstPage() = currentItem == 0

fun ViewPager.isLastPage() = currentItem == (adapter?.count?.minus(1))

fun ViewPager.nextPage() {
    if (currentItem < (adapter?.count?.minus(1)) ?: 0) {
        currentItem += 1
    }
}

fun ViewPager.previousPage() {
    if (currentItem > 0)
        currentItem -= 1
}

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}