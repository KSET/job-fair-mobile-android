package com.undabot.jobfair.core.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
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

    open fun showMessage(
        message: String,
        onDismissed: (() -> Unit)? = null
    ) {
        val snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT)
        if (onDismissed != null) {
            snackbar.addCallback(object : BaseTransientBottomBar.BaseCallback<Snackbar>() {
                override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                    super.onDismissed(transientBottomBar, event)
                    onDismissed()
                }
            })
        }
        snackbar.show()
    }

    abstract fun injectToAppComponent(applicationComponent: ApplicationComponent)
}