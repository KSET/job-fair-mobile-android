package com.undabot.jobfair.agenda.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.undabot.jobfair.R
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseFragment
import com.undabot.jobfair.events.entities.Event
import com.undabot.jobfair.events.list.view.EventsScreen
import com.undabot.jobfair.utils.setupWithViewPager
import kotlinx.android.synthetic.main.agenda_fragment.*

class AgendaFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.agenda_fragment, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewPager.adapter = AgendaPagerAdapter(context!!, childFragmentManager)
        viewPager.offscreenPageLimit = 2

        tabLayout.setupWithViewPager(
            viewPager = viewPager,
            marginBetween = 8
        )
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {}

    class AgendaPagerAdapter(
        context: Context,
        fragmentManager: FragmentManager
    ) : FragmentStatePagerAdapter(fragmentManager) {

        private val workshopsTitle = context.getString(R.string.workshops)
        private val presentationsTitle = context.getString(R.string.presentations)

        override fun getItem(position: Int): Fragment = when (position) {
            0 -> EventsScreen.newInstance(Event.Type.WORKSHOP)
            else -> EventsScreen.newInstance(Event.Type.PRESENTATION)
        }

        override fun getPageTitle(position: Int): String = when (position) {
            0 -> workshopsTitle
            else -> presentationsTitle
        }

        override fun getCount() = 2
    }
}
