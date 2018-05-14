package com.undabot.jobfair.utils.viewpager

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import com.undabot.jobfair.R
import com.undabot.jobfair.utils.disable
import com.undabot.jobfair.utils.enable
import com.undabot.jobfair.utils.isFirstPage
import com.undabot.jobfair.utils.isLastPage
import com.undabot.jobfair.utils.nextPage
import com.undabot.jobfair.utils.previousPage
import kotlinx.android.synthetic.main.navigatable_pager.view.*
import kotlinx.android.synthetic.main.navigatable_pager.view.pager as internalPager

class NavigableViewPager(
        context: Context, attributeSet: AttributeSet) : FrameLayout(context, attributeSet) {

    private lateinit var nextButton: TextView
    private lateinit var previousButton: TextView
    lateinit var viewPager: ViewPager

    init {
        init(context)
    }

    fun init(context: Context) {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.navigatable_pager, this, true)
        viewPager = view.internalPager
        previousButton = view.previous
        nextButton = view.next
        previousButton.setOnClickListener { previousPage() }
        nextButton.setOnClickListener { nextPage() }
        viewPager.addOnAdapterChangeListener({ _, _, _ -> updateButtonState() })
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                updateButtonState()
            }
        })
    }

    private fun nextPage() {
        viewPager.nextPage()
        updateButtonState()
    }

    private fun previousPage() {
        viewPager.previousPage()
        updateButtonState()
    }

    private fun updateButtonState() {
        when {
            viewPager.isFirstPage() && viewPager.isLastPage() -> {
                viewPagerNavigationContainer.visibility = View.GONE
                internalPager.setPadding(internalPager.paddingLeft,
                        internalPager.paddingTop,
                        internalPager.paddingRight,
                        0)
            }
            viewPager.isFirstPage() -> {
                nextButton.enable()
                previousButton.disable()
            }
            viewPager.isLastPage() -> {
                previousButton.enable()
                nextButton.disable()
            }
            else -> {
                nextButton.enable()
                previousButton.enable()
            }
        }
    }
}