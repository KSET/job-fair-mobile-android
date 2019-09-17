package com.undabot.jobfair.utils

import android.content.Context
import android.content.Intent
import android.util.DisplayMetrics
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.viewpager.widget.ViewPager

fun Context.canHandle(intent: Intent): Boolean =
        this.packageManager.queryIntentActivities(intent, 0).isNotEmpty()

fun TextView.setTextOrHideIfEmpty(@StringRes template: Int, text: String?, vararg otherViews: View) {
    if (text.isNullOrEmpty()) {
        this.visibility = View.GONE
        otherViews.forEach { it.visibility = View.GONE }
    } else {
        this.text = context.getString(template, text)
    }
}

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

fun Int.dpToPx(context: Context): Int {
    val displayMetrics = context.resources.displayMetrics
    return Math.round(this * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT))
}
