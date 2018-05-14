package com.undabot.jobfair.home.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class HomePagerAdapter(
    private val fragments: List<Fragment>,
    manager: FragmentManager
) : FragmentStatePagerAdapter(manager) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }
}
