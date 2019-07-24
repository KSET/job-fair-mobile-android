package com.undabot.jobfair.core.view

import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.undabot.jobfair.App
import com.undabot.jobfair.core.di.ApplicationComponent

abstract class BaseFragment : Fragment(), MenuItem.OnMenuItemClickListener {

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        injectToAppComponent(App.instance.applicationComponent)
    }

    open fun showGeneralErrorMessage() {
        (activity as BaseActivity).showGeneralErrorMessage()
    }

    open fun showMessage(message: String) {
        (activity as BaseActivity).showMessage(message)
    }

    abstract fun injectToAppComponent(applicationComponent: ApplicationComponent)

    override fun onMenuItemClick(it: MenuItem): Boolean {
        return false
    }
}