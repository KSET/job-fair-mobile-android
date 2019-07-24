package com.undabot.jobfair.support.assistance.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.undabot.jobfair.R
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseActivity
import com.undabot.jobfair.support.assistance.di.AssistanceModule
import com.undabot.jobfair.support.popup.SupportPopupActivity
import kotlinx.android.synthetic.main.assistance_activity.*
import kotlinx.android.synthetic.main.toolbar_details.*
import javax.inject.Inject

class AssistanceActivity : BaseActivity(), AssistanceContract.View {

    companion object {

        fun startWith(context: Context) {
            context.startActivity(Intent(context, AssistanceActivity::class.java))
        }
    }

    @Inject lateinit var coordinator: AssistanceContract.Coordinator
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.assistance_activity)
        setupToolbar()
        coordinator.bind(this)
        requestAssistanceButton.setOnClickListener {
            coordinator.assistanceRequested(assistanceInput.text.toString())
        }
    }

    private fun setupToolbar() {
        toolbar = findViewById(R.id.toolbar)
        toolbarTitle.text = getString(R.string.assistance)
        toolbar.setNavigationOnClickListener { super.onBackPressed() }
    }

    override fun displayAssistanceSent(note: String?) {
        SupportPopupActivity.startForAssistanceWith(this, note)
        finish()
    }

    override fun displayError() {
        showGeneralErrorMessage()
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(AssistanceModule()).inject(this)
    }
}
