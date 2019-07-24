package com.undabot.jobfair.support.drinks.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.undabot.jobfair.R
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseActivity
import com.undabot.jobfair.support.drinks.di.DrinksModule
import com.undabot.jobfair.support.drinks.view.adapter.DrinksAdapter
import com.undabot.jobfair.support.popup.SupportPopupActivity
import kotlinx.android.synthetic.main.drinks_activity.*
import kotlinx.android.synthetic.main.toolbar_details.*
import javax.inject.Inject

class DrinksActivity : BaseActivity(), DrinksContract.View {

    companion object {

        fun startWith(context: Context) {
            context.startActivity(Intent(context, DrinksActivity::class.java))
        }
    }

    @Inject lateinit var coordinator: DrinksContract.Coordinator
    private lateinit var toolbar: Toolbar
    private lateinit var adapter: DrinksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.drinks_activity)
        setupToolbar()
        coordinator.bind(this)
        coordinator.availableDrinksRequested()
    }

    override fun showDrinks(viewModels: List<DrinkViewModel>) {
        adapter = DrinksAdapter(viewModels)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        requestDrinksButton.setOnClickListener {
            val drinks = requestedDrinks()
            if (drinks.isNotEmpty()) {
                coordinator.drinksRequested(drinks)
            } else {
                showMessage(getString(R.string.drinks_empty_selection_message))
            }
        }
    }

    override fun showDrinksRequestSuccess(message: CharSequence) {
        SupportPopupActivity.startForDrinksWith(this, message)
        finish()
    }

    private fun requestedDrinks(): List<DrinkViewModel> =
        adapter.viewModels
            .filter { it.count > 0 }

    override fun showError() {
        showGeneralErrorMessage()
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbarTitle.text = getString(R.string.drinks)
        toolbar.setNavigationOnClickListener { super.onBackPressed() }
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(DrinksModule()).inject(this)
    }
}
