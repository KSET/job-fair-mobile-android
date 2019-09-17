package com.undabot.jobfair.account.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.undabot.jobfair.R
import com.undabot.jobfair.account.di.AccountModule
import com.undabot.jobfair.account.view.adapter.AccountAdapter
import com.undabot.jobfair.account.view.model.AccountViewModel
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseActivity
import com.undabot.jobfair.splash.view.SplashActivity
import kotlinx.android.synthetic.main.account_activity.*
import kotlinx.android.synthetic.main.toolbar_details.*
import javax.inject.Inject

class AccountActivity : BaseActivity(), AccountContract.View {

    companion object {

        fun startWith(context: Context) {
            context.startActivity(Intent(context, AccountActivity::class.java))
        }
    }

    @Inject lateinit var coordinator: AccountContract.Coordinator
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account_activity)
        setupToolbar()
        coordinator.bind(this)
        coordinator.accountInfoRequested()
        logoutButton.setOnClickListener { coordinator.signOutRequested() }
    }

    override fun restartApplication() {
        SplashActivity.startWith(this)
    }

    override fun show(viewModels: List<AccountViewModel>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = AccountAdapter(viewModels)
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(AccountModule()).inject(this)
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbarTitle.text= getString(R.string.account)
        toolbar.setNavigationOnClickListener { super.onBackPressed() }
    }
}
