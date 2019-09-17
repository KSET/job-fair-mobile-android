package com.undabot.jobfair.home.view

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import com.undabot.jobfair.R
import com.undabot.jobfair.about.view.AboutScreen
import com.undabot.jobfair.agenda.view.AgendaFragment
import com.undabot.jobfair.companies.list.view.CompaniesScreen
import com.undabot.jobfair.core.view.BaseFragment
import com.undabot.jobfair.news.list.view.NewsScreen

private val POSITION_TO_TAB = HashMap<Int, Tab>(5).apply {
    put(0, AgendaTab)
    put(1, NewsTab)
    put(2, EmptyTab)
    put(3, CompaniesTab)
    put(4, AboutTab)
}

sealed class Tab(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val fragmentFactory: () -> BaseFragment?
) {

    fun toNavigationItem(context: Context): AHBottomNavigationItem {
        val titleString = if (title > 0) context.getString(title) else ""
        return AHBottomNavigationItem(titleString, icon)
    }

    companion object {

        fun forPosition(position: Int): Tab? = POSITION_TO_TAB[position]

        fun getTabsWithEmpty() = listOf(AgendaTab, NewsTab, EmptyTab, CompaniesTab, AboutTab)
    }
}

object AgendaTab : Tab(R.string.agenda, R.drawable.ic_agenda, { AgendaFragment() })
object NewsTab : Tab(R.string.news, R.drawable.ic_news, { NewsScreen() })
object EmptyTab : Tab(0, R.drawable.ic_middle_tab_background, { null })
object CompaniesTab : Tab(R.string.companies, R.drawable.ic_companies, { CompaniesScreen() })
object AboutTab : Tab(R.string.about, R.drawable.ic_about, { AboutScreen() })