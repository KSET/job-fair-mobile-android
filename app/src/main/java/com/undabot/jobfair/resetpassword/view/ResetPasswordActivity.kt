package com.undabot.jobfair.resetpassword.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.Toolbar
import com.undabot.jobfair.R
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseActivity
import com.undabot.jobfair.resetpassword.di.ResetPasswordModule
import kotlinx.android.synthetic.main.reset_password_activity.*
import javax.inject.Inject

class ResetPasswordActivity : BaseActivity(), ResetPasswordContract.View {

    companion object {
        private const val EMAIL_EXTRA = "email"
        fun startWith(context: Context, email: String?) {
            val intent = Intent(context, ResetPasswordActivity::class.java).apply {
                putExtra(EMAIL_EXTRA, email)
            }
            context.startActivity(intent)
        }
    }

    @Inject lateinit var coordinator: ResetPasswordContract.Coordinator

    private lateinit var toolbar: Toolbar

    private var email: String
        get() = emailInput.text.toString()
        set(value) = emailInput.setText(value)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reset_password_activity)
        toolbar = findViewById(R.id.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { super.onBackPressed() }
        coordinator.bind(this)

        email = intent.extras?.getString(EMAIL_EXTRA).orEmpty()

        resetPasswordButton.setOnClickListener {
            hideKeyboard()
            coordinator.requestPasswordReset(email)
        }
    }

    override fun onDestroy() {
        coordinator.unbind(this)
        super.onDestroy()
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(ResetPasswordModule()).inject(this)
    }

    override fun displaySuccessfulReset() {
        val message = getString(R.string.reset_password_screen_successful_reset)
        showMessage(message) {
            finish()
        }
    }

    override fun showGeneralError() {
        showGeneralErrorMessage()
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = currentFocus
        if (view == null) {
            view = View(this)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}