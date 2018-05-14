package com.undabot.jobfair.utils

object FragmentUtils {

    /**
     * Creates tag which is automatically created for [android.support.v4.app.Fragment] in
     * [android.support.v4.app.FragmentPagerAdapter].
     *
     * @param viewPagerResId - view pager id in XML
     * @param positionInViewPager - position on which fragment is placed in viewPager
     * @return fragment tag for finding fragments added to view pager
     */
    fun getFragmentTag(viewPagerResId: Int, positionInViewPager: Int): String {
        return "android:switcher:$viewPagerResId:$positionInViewPager"
    }
}
