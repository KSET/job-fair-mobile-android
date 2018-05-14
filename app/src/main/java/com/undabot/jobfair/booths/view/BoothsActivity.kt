package com.undabot.jobfair.booths.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.undabot.jobfair.R
import com.undabot.jobfair.booths.entities.LocationInfo
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseActivity

class BoothsActivity : BaseActivity() {

    companion object {

        private val EXTRA_BOOTH_LOCATION = "extra_booth_location"

        fun startWith(context: Context, boothLocationInfo: LocationInfo) {
            val intent = Intent(context, BoothsActivity::class.java)
            intent.putExtra(EXTRA_BOOTH_LOCATION, boothLocationInfo)
            context.startActivity(intent)
        }
    }

    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.booths_activity)
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.booths)
        toolbar.setNavigationOnClickListener { onBackPressed() }

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainer,
                BoothsScreen.newInstance(intent.getParcelableExtra(EXTRA_BOOTH_LOCATION)))
        fragmentTransaction.commit()
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        // Nothing to inject
    }
}