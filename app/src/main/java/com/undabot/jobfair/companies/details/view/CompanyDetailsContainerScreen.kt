package com.undabot.jobfair.companies.details.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.widget.Toolbar
import com.undabot.jobfair.R
import com.undabot.jobfair.companies.view.models.CompanyViewModel
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseActivity
import kotlinx.android.synthetic.main.screen_company_details_container.*

class CompanyDetailsContainerScreen : BaseActivity() {

    companion object {
        private const val COMPANY_ITEMS = "COMPANY_ITEMS"
        private const val CHOSEN_ITEM = "CHOSEN_ITEM"

        fun startWith(context: Context, companies: ArrayList<CompanyViewModel>, position: Int = 0) {
            context.startActivity(Intent(context, CompanyDetailsContainerScreen::class.java)
                    .apply {
                        putExtra(COMPANY_ITEMS, companies)
                        putExtra(CHOSEN_ITEM, position)
                    })
        }
    }

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.screen_company_details_container)
        setupToolbar()
        setupView()
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.companies)
        toolbar.setNavigationOnClickListener { super.onBackPressed() }
    }

    private fun setupView() {
        val list = intent.getParcelableArrayListExtra<CompanyViewModel>(COMPANY_ITEMS)
        val position = intent.getIntExtra(CHOSEN_ITEM, 0)
        detailPager.viewPager.adapter = fragmentPagerAdapter(list)
        detailPager.viewPager.setCurrentItem(position, false)
    }

    private fun fragmentPagerAdapter(list: ArrayList<CompanyViewModel>) =
            object : FragmentPagerAdapter(supportFragmentManager) {
                override fun getCount(): Int = list.size

                override fun getItem(position: Int): Fragment =
                        CompanyDetailsScreen.with(list[position])
            }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
    }
}
