package com.undabot.jobfair.core.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import com.undabot.jobfair.App
import com.undabot.jobfair.R
import com.undabot.jobfair.core.di.ApplicationComponent

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectToAppComponent(App.instance.applicationComponent)
    }

    open fun showGeneralErrorMessage() {
        showMessage(getString(R.string.general_error_message))
    }

    open fun showMessage(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
    }

    abstract fun injectToAppComponent(applicationComponent: ApplicationComponent)
}