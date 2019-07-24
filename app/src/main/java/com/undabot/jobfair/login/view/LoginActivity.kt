package com.undabot.jobfair.login.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.Toolbar
import com.undabot.jobfair.R
import com.undabot.jobfair.core.di.ApplicationComponent
import com.undabot.jobfair.core.view.BaseActivity
import com.undabot.jobfair.home.view.MainActivity
import com.undabot.jobfair.login.di.LoginModule
import com.undabot.jobfair.resetpassword.view.ResetPasswordActivity
import kotlinx.android.synthetic.main.login_activity.*
import javax.inject.Inject

class LoginActivity : BaseActivity(), LoginContract.View {

    companion object {
        fun startWith(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject lateinit var coordinator: LoginContract.Coordinator

    private lateinit var toolbar: Toolbar

    private val email: String
        get() = emailInput.text.toString()
    private val password: String
        get() = passwordInput.text.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        toolbar = findViewById(R.id.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { super.onBackPressed() }
        coordinator.bind(this)

        loginButton.setOnClickListener {
            hideKeyboard()
            coordinator.loginRequested(email, password)
        }
        forgotPasswordButton.setOnClickListener { ResetPasswordActivity.startWith(this, email) }
        passwordInput.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard()
                coordinator.loginRequested(email, password)
                true
            } else {
                false
            }
        }
    }

    override fun onDestroy() {
        coordinator.unbind(this)
        super.onDestroy()
    }

    override fun injectToAppComponent(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(LoginModule()).inject(this)
    }

    override fun openHomeScreen() {
        MainActivity.startWith(this)
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